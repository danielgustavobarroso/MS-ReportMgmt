package com.retooling.report.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import com.retooling.report.entity.Chicken;
import com.retooling.report.entity.Egg;
import com.retooling.report.entity.Farm;

@Service
public class ApiCall {
	
	private static final Logger logger = LoggerFactory.getLogger(ApiCall.class);
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${api.microservice.farm}")
	private String urlFarm;
	
	@Value("${api.microservice.egg}")
	private String urlEgg;
	
	@Value("${api.microservice.chicken}")
	private String urlChicken;

	@Value("${api.microservice.date-simulator}")
	private String urlDateSimulator;
	
	@Value("${api.microservice.use-date-simulator}")
	private boolean useDateSimulator;
	
	public ApiCall() {
		super();
	}
	
	public Farm getFarm(String id) {
		logger.info("Service - Calling getFarm...");
		return restTemplate.getForObject(urlFarm+"/{id}", Farm.class, id);
	}
	
	public List<Egg> getEggs(String idFarm) {
		try {
			logger.info("Service - Calling getEggs...");
			EggState eggAvailable = EggState.Available;
			return Arrays.asList(restTemplate.getForObject(urlEgg+"/{idFarm}", Egg[].class, idFarm))
				.stream().filter(c -> c.getState().equals(eggAvailable.getState())).collect(Collectors.toList());
		} catch (HttpClientErrorException.NotFound ex) {
			return new ArrayList<>();
		}
	}

	public List<Chicken> getChickens(String idFarm) {
		try {
			logger.info("Service - Calling getChickens...");
			ChickenState chickenAvailable = ChickenState.Available;
			return Arrays.asList(restTemplate.getForObject(urlChicken+"/{idFarm}", Chicken[].class, idFarm))
				.stream().filter(c -> c.getState().equals(chickenAvailable.getState())).collect(Collectors.toList());
		} catch (HttpClientErrorException.NotFound ex) {
			return new ArrayList<>();
		}
	}

	public Date getDate() throws ParseException {		
		logger.info("Service - Calling getDate...");
		if (useDateSimulator) {
			String dateStr = restTemplate.getForObject(urlDateSimulator+"/get-date", String.class);
			return (new SimpleDateFormat("yyyyMMddHHmmss").parse(dateStr));
		} else {
			return new Date();
		}
	}
	
}
