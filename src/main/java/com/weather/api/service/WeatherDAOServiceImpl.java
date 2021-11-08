package com.weather.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.weather.api.entities.location.WeatherDAO;
import com.weather.api.entities.location.WeatherDTO;
import com.weather.api.exceptions.LocationNotProvidedException;
import com.weather.api.repository.WeatherRepository;

@Component
public class WeatherDAOServiceImpl implements WeatherDAOService{
	@Autowired
	WeatherRepository weatherRepository;

	@Override
	public WeatherDAO createWeatherDetail(WeatherDTO weatherDTO) {

		WeatherDAO weatherDAO = new WeatherDAO(
				weatherDTO.getLocation(),
				weatherDTO.getName(),
				weatherDTO.getCountry().getName(),
				weatherDTO.getWeatherDescriptiom()
				);

		weatherRepository.save(weatherDAO);

		return weatherDAO;
	}

	@Override
	public List<WeatherDAO> findByNameAndCountry(String location) {
		String[] parts;

		try {
			parts = location.split(",");
			if(parts[0].equals("") || parts.length != 2) {
				throw  new LocationNotProvidedException();
			}

		} catch (NullPointerException e) {
			throw new LocationNotProvidedException();
		}

		return weatherRepository.findByNameAndCountry(parts[0], parts[1]);
	}
}
