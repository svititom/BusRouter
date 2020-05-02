package com.svititom.BusRouter.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.svititom.BusRouter.model.BusRoutes;
import com.svititom.BusRouter.model.BusStops;
import com.svititom.BusRouter.repository.BusRoutePointRepository;
import com.svititom.BusRouter.repository.BusStopRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;

@Service
public class LtaConnectionService {

    public static final String accountKeyEnvVar = "LTA_DATAMALL_ACCOUNT_KEY";
    public static final String baseUrl = "http://datamall2.mytransport.sg/ltaodataservice";
    public static final String skipParam = "?$skip=";

    @Autowired
    private BusStopRepository busStopRepository;
    @Autowired
    private BusRoutePointRepository busRoutePointRepository;

    @Autowired
    RestTemplate restTemplate;


    @Value("${"+accountKeyEnvVar+"}")
    private String accountKey;

//    @PostConstruct
//    public void init(){
//        System.out.println("\n\n\nAccount Key is: " + accountKey);
//    }

    public static final Logger log = LoggerFactory.getLogger(LtaConnectionService.class);

    public BusRoutes downloadBusRoutes(int skipCount) throws JsonProcessingException {
        String url = baseUrl + "/BusRoutes" + skipParam + skipCount;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("accept", "application/json");
        headers.set("AccountKey", accountKey);
        HttpEntity entity = new HttpEntity(headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            ObjectMapper mapper = new ObjectMapper();
            log.debug("Downloaded Json: " + response.getBody());
            BusRoutes busRoutes = mapper.readValue(response.getBody(), BusRoutes.class);
            log.info(busRoutes.toString());
            return busRoutes;
        } else {
            log.warn("Failed to download stops");
        }
        return null;
    }

    public void updateBusRoutes() throws JsonProcessingException {
//        int busRoutePointCount = 0;
        System.out.println("Starting download");
        int busRoutePointCount = 0;
        long currentlyDownloaded = 0;
        // The Api returns 500 results, if it's less, we don't need to paginate anymore
        do{
            System.out.println("Waiting for download:");
            BusRoutes busRoutes = downloadBusRoutes(busRoutePointCount);
            if(busRoutes != null){
                currentlyDownloaded = busRoutes.getBusRoutePoints().size();
                busRoutePointCount += currentlyDownloaded;
                System.out.println(busRoutes.toString());
                System.out.println("Waiting for save:");
                busRoutePointRepository.saveAll(busRoutes.getBusRoutePoints());
//                busRoutePointRepository.save(busRoutes.getBusRoutePoints().get(0));
            } else {
                break;
            }
            System.out.println("Downloaded " + busRoutePointCount + " bus route points");
        } while(currentlyDownloaded == 500);
    }

    private BusStops downoadBusStops(int skipCount) throws JsonProcessingException {
        String url = baseUrl + "/BusStops" + skipParam + skipCount;
        System.out.println("Downloading from: " + url);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("accept", "application/json");
        headers.set("AccountKey", accountKey);


        HttpEntity entity = new HttpEntity(headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            ObjectMapper mapper = new ObjectMapper();
            BusStops busStops = mapper.readValue(response.getBody(), BusStops.class);
            log.info(busStops.toString());
            return busStops;
        } else {
            log.warn("Failed to download stops");
        }
        return null;
    }

    /**
     * Download and Update the bus stops in db
     * @throws JsonProcessingException
     */
    public void updateBusStops() throws JsonProcessingException {
        int busStopCount = 0;
        int currentlyDownloaded = 0;
        // The Api returns 500 results, if it's less, we don't need to paginate anymore
        do{
            BusStops busStops = downoadBusStops(busStopCount);
            if(busStops != null){
                currentlyDownloaded = busStops.getBusStops().size();
                busStopCount += currentlyDownloaded;
                busStopRepository.saveAll(busStops.getBusStops());
            } else {
                break;
            }
        } while(currentlyDownloaded == 500);
        System.out.println("Downloaded " + busStopCount + " bus stops");
    }


}
