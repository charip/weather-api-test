package com.weather.api.entities.location;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

public class Country {
    @JsonProperty("country")
    @Getter @Setter
    private String name;

    public Country() {
    }

    public Country(String name) {
        this.name = name;
    }

}
