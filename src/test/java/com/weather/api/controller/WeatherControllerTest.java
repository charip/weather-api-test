package com.weather.api.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.weather.api.entities.location.WeatherDAO;
import com.weather.api.entities.weather.WeatherInfo;
import com.weather.api.service.WeatherService;


@WebMvcTest(WeatherController.class)

class WeatherControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private WeatherService service;



	@Test
	void testShouldReturnWEatherDetailForLocation() throws Exception {
		WeatherDAO dao = new WeatherDAO();
		List<WeatherInfo> weatherInfo=new ArrayList<WeatherInfo>();
		weatherInfo.add(new WeatherInfo("Fog"));
		dao.setLocation("London");
		dao.setDescription("Fog");
		when(service.getWeatherDetails("London,uk")).thenReturn(dao);

		MvcResult result= this.mockMvc.perform(get("/weather/").header("API_KEY", "apiKey2").param("location", "London,uk")).andDo(print())
				.andExpect(status().isOk())
				.andReturn();
		assertNotNull(result);

	}
	
	
}
