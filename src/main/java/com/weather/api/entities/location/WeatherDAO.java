package com.weather.api.entities.location;

import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
@Table(name = "WeatherDetails")
public class WeatherDAO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long locationId;
    private String location;
    private String name;
    private String country;
    private String description;
   
	public WeatherDAO(String location, String name, String country, String decsription) {
        this.location = location;
        this.name = name;
        this.country = country;
        this.description = decsription;
    }

    public String getNameCountry() {
        return name + ", " + country;
    }
}
