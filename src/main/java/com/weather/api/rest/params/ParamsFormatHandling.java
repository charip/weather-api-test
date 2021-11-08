package com.weather.api.rest.params;

import org.apache.commons.text.WordUtils;
import org.springframework.stereotype.Component;

import com.weather.api.exceptions.LocationNotProvidedException;

import java.text.Normalizer;

@Component
public class ParamsFormatHandling implements ParamsFormat {

	@Override
	public String fixformat(String params) {
		params = Normalizer.normalize(params, Normalizer.Form.NFD);
		params = params.replaceAll("[^\\p{ASCII}]", "");

		String[] parts = params.split(",");

		String result;

		if (parts.length >= 1) {
			String city = WordUtils.capitalizeFully(parts[0]);
			String country = parts[1].toUpperCase();

			result = city + "," + country;
		} else {
			throw new LocationNotProvidedException();
			
		}

		return result;
	}
}
