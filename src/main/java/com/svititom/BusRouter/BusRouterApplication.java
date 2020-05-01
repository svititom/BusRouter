package com.svititom.BusRouter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.svititom.BusRouter.model.BusStop;
import com.svititom.BusRouter.model.BusStops;
import com.svititom.BusRouter.repository.BusStopRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import javax.persistence.PersistenceContext;

@SpringBootApplication
public class BusRouterApplication {

	public static final Logger log = LoggerFactory.getLogger(BusRouterApplication.class);

	@Autowired
	private BusStopRepository busStopRepository;

	@Autowired
	private Environment env;

	public static void main(String[] args) {
		SpringApplication.run(BusRouterApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder){
		return builder.build();
	}

	private BusStops downloadBusStops(RestTemplate restTemplate, int skipCount) throws JsonProcessingException {
		String url = "http://datamall2.mytransport.sg/ltaodataservice/BusStops" + "?$skip=" + skipCount;
		System.out.println("Downloading from: " + url);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("accept", "application/json");
//		headers.set("AccountKey", "I47DeBboSLWraehH3miSOw==");
		headers.set("AccountKey", env.getProperty("LTA_DATAMALL_ACCOUNT_KEY"));


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

	private void updateBusStops(RestTemplate restTemplate) throws JsonProcessingException {
		int busStopCount = 0;
		int currentlyDownloaded = 0;
		// The Api returns 500 results, if it's less, we don't need to paginate anymore
		do{
			BusStops busStops = downloadBusStops(restTemplate, busStopCount);
			if(busStops != null){
				currentlyDownloaded = busStops.getBusStops().size();
				busStopCount += currentlyDownloaded;
				busStopRepository.saveAll(busStops.getBusStops());
			} else {
				break;
			}
		} while(currentlyDownloaded == 500);
	}

	@Bean
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
		return args -> {
			updateBusStops(restTemplate);
		};

	}



}
