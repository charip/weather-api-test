package com.weather.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.weather.api.entities.location.WeatherDAO;
import com.weather.api.entities.location.WeatherDTO;
import com.weather.api.exceptions.LocationNotFoundException;
import com.weather.api.exceptions.LocationNotProvidedException;
import com.weather.api.rest.params.ParamsFormat;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class WeatherService {
	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	WeatherDAOService locationService;
	@Autowired
	ParamsFormat format;

	@Value("${resource.weather_url}")
	private String weatherURL;

	@Value("${resource.api_key}")
	private String weatherAPIKey;

	public WeatherDTO getWeather(String location) {
		String url = weatherURL + "/weather" + "?appid=" + weatherAPIKey + "&q="+ location;

		WeatherDTO local;

		try {
			local = restTemplate.getForObject(url, WeatherDTO.class);
		} catch (HttpClientErrorException e) {
			throw new LocationNotFoundException();
		}

		String[] parts = location.split(",");

		if(parts[0].equals("") || parts.length != 2) {
			throw  new LocationNotProvidedException();
		}

		assert local != null;
		local.setLocation(parts[0]);

		return local;
	}

	public WeatherDAO getWeatherDetails(String location) throws LocationNotFoundException,LocationNotProvidedException{
		WeatherDAO weatherDAO;
		WeatherDTO weatherDTO;
		// Transform the location request param format
		location = format.fixformat(location);

		/* Check if the location provided already exists in the database */
		log.info("Fetching details from DB");
		List<WeatherDAO> results = locationService.findByNameAndCountry(location);

		/* In case the weather detail doesn't exist in DB */
		if(results.isEmpty()) {
			
			log.info("Getting details from opwen weather API");
			weatherDTO=getWeather(location);
			weatherDTO.setLocation(weatherDTO.getLocationCountry());
			
			/* Save the weather detail in the DB */
			weatherDAO = locationService.createWeatherDetail(weatherDTO);

		} else {
			/* Get the result of the weather info for the location provided from db */
			weatherDAO=results.get(0);
		}
		return weatherDAO;
	}


}
