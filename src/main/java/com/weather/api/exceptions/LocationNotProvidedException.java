package com.weather.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Location was not provided in the correct format")
public class LocationNotProvidedException extends RuntimeException {
}
