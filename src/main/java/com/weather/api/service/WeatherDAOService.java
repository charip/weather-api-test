package com.weather.api.service;

import com.weather.api.entities.location.WeatherDAO;
import com.weather.api.entities.location.WeatherDTO;

import java.util.List;

public interface WeatherDAOService {
    WeatherDAO createWeatherDetail(WeatherDTO locationDTO);
    List<WeatherDAO> findByNameAndCountry(String location);
}
