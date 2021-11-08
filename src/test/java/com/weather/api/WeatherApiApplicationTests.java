package com.weather.api;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.weather.api.controller.WeatherController;

@SpringBootTest
class WeatherApiApplicationTests {
	
	@Autowired WeatherController weatherController;

	@Test
	void contextLoads() {
		assertThat(weatherController).isNotNull();
	}

}
