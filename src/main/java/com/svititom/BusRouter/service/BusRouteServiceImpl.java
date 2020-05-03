package com.svititom.BusRouter.service;

import com.svititom.BusRouter.model.BusRoute;
import com.svititom.BusRouter.model.lta.BusRoutes;
import com.svititom.BusRouter.repository.BusRouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Servicet
public class BusRouteServiceImpl implements BusRouteService {

    @Autowired
    BusRouteRepository busRouteRepository;

    @Override
    public void updateBusRoutes(BusRoutes busRoutes) {

    }

    @Override
    public BusRoute getBusRoute(String serviceNumber, int direction) {
        return busRouteRepository.getBusRouteByServiceNumberAndDirection(serviceNumber, direction);
    }
}
