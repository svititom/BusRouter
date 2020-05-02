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


	public void updatedDb() throws Exception{
		ltaConnectionService.updateBusStops();
		ltaConnectionService.updateBusRoutes();
	}


	@Bean
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
		return args -> {
			updatedDb();
		};

	}



}
