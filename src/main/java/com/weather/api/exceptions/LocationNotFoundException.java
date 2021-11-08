package com.weather.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Location provided not found")
public class LocationNotFoundException extends RuntimeException {

}
