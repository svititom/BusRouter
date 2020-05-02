package com.svititom.BusRouter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.svititom.BusRouter.model.BusStops;
import com.svititom.BusRouter.repository.BusStopRepository;
import com.svititom.BusRouter.service.LtaConnectionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class BusRouterApplication {

	public static final Logger log = LoggerFactory.getLogger(BusRouterApplication.class);

	@Autowired
	private BusStopRepository busStopRepository;

	@Autowired
	private Environment env;

	@Autowired
	LtaConnectionService ltaConnectionService;

	public static void main(String[] args) {
		SpringApplication.run(BusRouterApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder){
		return builder.build();
	}



	private void updateBusStops(RestTemplate restTemplate, String accountKey) throws JsonProcessingException {
		int busStopCount = 0;
		int currentlyDownloaded = 0;
		// The Api returns 500 results, if it's less, we don't need to paginate anymore
		do{
			BusStops busStops = ltaConnectionService.downoadBusStops(restTemplate, accountKey, busStopCount);
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

	@Bean
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
		return args -> {
			String accountKey = env.getProperty("LTA_DATAMALL_ACCOUNT_KEY");
			updateBusStops(restTemplate, accountKey);
		};

	}



}
