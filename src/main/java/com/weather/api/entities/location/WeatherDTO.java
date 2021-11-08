package com.weather.api.entities.location;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.weather.api.entities.weather.WeatherInfo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class WeatherDTO {

    private String location;
    
    private String name;

    @JsonProperty("sys")
    private Country country;

    @JsonProperty("weather")
    private List<WeatherInfo> weatherInfos;
    
    public WeatherDTO(String name,Country country) {
        this.name = name;
        this.country = country;
    }

    public String getLocationCountry() {
        return location + "," + country.getName();
    }
    
    public String getWeatherDescriptiom() {
    	return weatherInfos.get(0).getDescription();
    }
}
