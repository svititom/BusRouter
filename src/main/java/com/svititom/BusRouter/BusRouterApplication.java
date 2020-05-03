package com.svititom.BusRouter;

import com.svititom.BusRouter.service.BusRouteService;
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
	BusRouteService busRouteService;



	public static void main(String[] args) {
		SpringApplication.run(BusRouterApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder){
		return builder.build();
	}


	public void updatedDb() throws Exception{
		busRouteService.updateBusRoutes();
		System.out.println("Got all these bus routes: ");
		busRouteService.getAllBusRoutes().forEach(System.out::println);
		System.out.println("For example, route 8 in direction 1 is: ");
		System.out.println(busRouteService.getBusRoute("8", 1).toString());
	}



	@Bean
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
		return args -> {
			updatedDb();
		};

	}



}
