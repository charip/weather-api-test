package com.weather.api.entities.weather;

public class WeatherInfo {
	private String description;

	public WeatherInfo() {

	}

	public WeatherInfo(String description) {
		this.description = description;

	}


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
