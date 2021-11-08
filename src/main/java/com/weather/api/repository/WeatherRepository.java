package com.weather.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.weather.api.entities.location.WeatherDAO;

public interface WeatherRepository extends JpaRepository<WeatherDAO, Long> {
   
	WeatherDAO findById(long id);
    List<WeatherDAO> findByNameAndCountry(String name, String country);
}
