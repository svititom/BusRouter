package com.svititom.BusRouter.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.svititom.BusRouter.model.BusRoute;
import com.svititom.BusRouter.model.lta.BusRoutePoint;
import com.svititom.BusRouter.model.lta.BusRoutePoints;
import com.svititom.BusRouter.model.lta.BusStop;
import com.svititom.BusRouter.model.lta.BusStops;
import com.svititom.BusRouter.repository.BusRouteRepository;
import com.svititom.BusRouter.repository.BusStopRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.swing.text.html.parser.Entity;
import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BusRouteServiceImpl implements BusRouteService {

    public static final Logger log = LoggerFactory.getLogger(BusRouteServiceImpl.class);

    @Autowired
    BusRouteRepository busRouteRepository;

    @Autowired
    BusStopRepository busStopRepository;

    @Autowired
    LtaConnectionService ltaConnectionService;

    private List<BusRoute> convertBusRoutePoints(BusRoutePoints busRoutePoints){
        /*
         * We want to collect the BusRoutePoints, which each represent a single stop along a bus route,
         * into Bus Routes, which represent a whole Bus Route
         * We need to keep track of the service number, and the direction, so we create a nested map
         */
        Map<String, Map<Integer, BusRoute>> busRouteMap = new HashMap<>();
        // Since the BusStops in the BusRoute are an ordered list, sort the BusRoutePoints
        Collections.sort(busRoutePoints.getBusRoutePoints(), Comparator.comparingInt(BusRoutePoint::getStopSequence));
        for (BusRoutePoint busRoutePoint : busRoutePoints.getBusRoutePoints()) {
            Map<Integer, BusRoute> busServiceMap = busRouteMap.computeIfAbsent(busRoutePoint.getServiceNumber(), s -> new HashMap<>());
            BusRoute busRoute = busServiceMap.computeIfAbsent(busRoutePoint.getDirection()
                    , direction -> new BusRoute(busRoutePoint.getServiceNumber(), busRoutePoint.getOperator(), busRoutePoint.getDirection()));

            int busStopCode;
            try {
                busStopCode = Integer.parseInt(busRoutePoint.getBusStopCode());
            } catch (NumberFormatException e){
                // Unfortunately the API returns 2 BusRoutePoints with bus stop code = "CTE" every other one is 5 digit number
                // Since there is no mapping for this bus stop, we can just throw it away
                log.warn("Failed to parse bus stop code for: " + busRoutePoint.toString() + "\n");
                continue;
            } catch (Exception e){
                log.error("Well dayum, didn't exect this:", e);
                continue;
            }
            BusStop busStop = busStopRepository.findBusStopByBusStopCode(busStopCode);
            if (busStop == null) {
                log.error("Could not find bus stop with code: " + busRoutePoint.getBusStopCode() + " for " + busRoutePoint.toString());
            }
            busRoute.getBusStops().add(busStop);
        }
        // And flatten the map into a single list
        List<BusRoute> busRoutes = new ArrayList<>();
        for(Map<Integer, BusRoute> busServiceMap : busRouteMap.values()){
            for(BusRoute busRoute : busServiceMap.values()){
                busRoutes.add(busRoute);
            }
        }
        return busRoutes;
    }

    private void updateBusStops() throws JsonProcessingException {
        BusStops busStops = ltaConnectionService.downloadBusStops();
        busStopRepository.saveAll(busStops.getBusStops());
    }

    @Override
    public void updateBusRoutes() {
        try {
            // todo add some checking, like update only once a day
            updateBusStops();

            BusRoutePoints busRoutePoints = ltaConnectionService.downloadBusRoutes();
            List<BusRoute> busRoutes = convertBusRoutePoints(busRoutePoints);
            busRouteRepository.saveAll(busRoutes);


        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }


    /**
     * Gets a single bus route, fully initialized
     */
    @Override
    @Transactional
    public BusRoute getBusRoute(String serviceNumber, int direction) {
        BusRoute busRoute = busRouteRepository.getBusRouteByServiceNumberAndDirection(serviceNumber, direction);
        busRoute.getBusStops().size(); // Force initialization of bus stops
        return busRoute;
    }

    /**
     * Gets all the bus routes, bus stops are uninitiaized
     */
    @Override
    public List<BusRoute> getAllBusRoutes() {
        return busRouteRepository.findAll();
    }
}
