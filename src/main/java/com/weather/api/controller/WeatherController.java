package com.weather.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.weather.api.entities.location.WeatherDAO;
import com.weather.api.exceptions.LocationNotFoundException;
import com.weather.api.exceptions.LocationNotProvidedException;
import com.weather.api.service.WeatherService;

@RestController
@RequestMapping("/weather")
public class WeatherController {

	@Autowired
	private WeatherService weatherService;
	
	@GetMapping
	public WeatherDAO getWeatherByLocation(@RequestParam("location") String location) {
		WeatherDAO dao=null;
		try {
		dao= weatherService.getWeatherDetails(location);
		
		}
		catch (LocationNotFoundException e) {
			throw new ResponseStatusException(
			           HttpStatus.NOT_FOUND, "Location provided not found",e);		}
		catch (LocationNotProvidedException e) {
			throw new ResponseStatusException(
			           HttpStatus.NOT_FOUND, "Location was not provided in the correct format",e);
		}
		catch (Exception e) {
			throw new ResponseStatusException(
			           HttpStatus.NOT_FOUND, "Weather details not found",e);
		}
		return dao;
	}
}
