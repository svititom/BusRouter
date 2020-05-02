package com.svititom.BusRouter.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.svititom.BusRouter.model.BusStops;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LtaConnectionService {

    public static final Logger log = LoggerFactory.getLogger(LtaConnectionService.class);

    public BusStops downoadBusStops(RestTemplate restTemplate, String accountKey, int skipCount) throws JsonProcessingException {
        String url = "http://datamall2.mytransport.sg/ltaodataservice/BusStops" + "?$skip=" + skipCount;
        System.out.println("Downloading from: " + url);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("accept", "application/json");
//		headers.set("AccountKey", "I47DeBboSLWraehH3miSOw==");
        headers.set("AccountKey", accountKey);


        HttpEntity entity = new HttpEntity(headers);
        System.out.println(headers.toString());

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
}
