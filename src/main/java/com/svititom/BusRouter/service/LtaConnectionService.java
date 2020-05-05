package com.svititom.BusRouter.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.svititom.BusRouter.model.lta.BusRoutePoint;
import com.svititom.BusRouter.model.lta.BusRoutePoints;
import com.svititom.BusRouter.model.lta.BusStops;
import com.svititom.BusRouter.repository.BusStopRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LtaConnectionService {

    public static final String accountKeyEnvVar = "LTA_DATAMALL_ACCOUNT_KEY";
    public static final String baseUrl = "http://datamall2.mytransport.sg/ltaodataservice";
    public static final String skipParam = "?$skip=";


    @Autowired
    RestTemplate restTemplate;


    @Value("${"+accountKeyEnvVar+"}")
    private String accountKey;

//    @PostConstruct
//    public void init(){
//        System.out.println("\n\n\nAccount Key is: " + accountKey);
//    }

    public static final Logger log = LoggerFactory.getLogger(LtaConnectionService.class);

    public BusRoutePoints downloadBusRoutes(int skipCount) throws JsonProcessingException {
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
            BusRoutePoints busRoutePoints = mapper.readValue(response.getBody(), BusRoutePoints.class);
            log.info(busRoutePoints.toString());
            return busRoutePoints;
        } else {
            log.warn("Failed to download stops");
        }
        return null;
    }

    public BusRoutePoints downloadBusRoutes() throws JsonProcessingException {
        BusRoutePoints busRoutePoints = new BusRoutePoints();
        System.out.println("Starting download");
        int busRoutePointCount = 0;
        long currentlyDownloaded = 0;
        // Download all of the data ponits
        do{
            BusRoutePoints downloadBusRoutePoints = downloadBusRoutes(busRoutePointCount);
            if(downloadBusRoutePoints != null){
                currentlyDownloaded = downloadBusRoutePoints.getBusRoutePoints().size();
                busRoutePointCount += currentlyDownloaded;
                busRoutePoints.getBusRoutePoints().addAll(downloadBusRoutePoints.getBusRoutePoints());

            } else {
                break;
            }
            System.out.println("Downloaded " + busRoutePointCount + " bus route points");
            // 500 is the max number of results returned by the api, if it's less, we're at the end of pagination
        } while(currentlyDownloaded == 500);
        return busRoutePoints;
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
    public BusStops downloadBusStops() throws JsonProcessingException {
        int busStopCount = 0;
        int currentlyDownloaded = 0;
        BusStops busStops = new BusStops();

        // The Api returns 500 results, if it's less, we don't need to paginate anymore
        do{
            BusStops downloadedBusStops = downoadBusStops(busStopCount);
            if(busStops != null){
                currentlyDownloaded = downloadedBusStops.getBusStops().size();
                busStopCount += currentlyDownloaded;
                busStops.getBusStops().addAll(downloadedBusStops.getBusStops());
            } else {
                break;
            }
        } while(currentlyDownloaded == 500);
        System.out.println("Downloaded " + busStopCount + " bus stops");
        return busStops;
    }



}
