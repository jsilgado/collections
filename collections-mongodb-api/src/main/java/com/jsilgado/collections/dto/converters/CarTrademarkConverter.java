package com.jsilgado.collections.dto.converters;

import java.util.ArrayList;
import java.util.List;

import com.jsilgado.collections.domain.CarTrademark;
import com.jsilgado.collections.dto.CarTrademarkDTO;

public class CarTrademarkConverter {

	public static CarTrademark toEntity( CarTrademarkDTO input ) {

		CarTrademark output = null;

		if (input != null) {
			output = new CarTrademark();
			output.setId(input.getId());
			output.setName(input.getName());
			output.setIdImage(input.getIdImage());
		}

		return output;

	}

	public static CarTrademarkDTO toDTO( CarTrademark input ) {

		CarTrademarkDTO output = null;

		if (input != null) {
			output = new CarTrademarkDTO();
			output.setId(input.getId());
			output.setName(input.getName());
			output.setIdImage(input.getIdImage());
		}

		return output;

	}

	public static List<CarTrademarkDTO> toDTO ( List<CarTrademark> input ) {

		List<CarTrademarkDTO> output = null;

		if (input != null) {
			output = new ArrayList<>();

			for (CarTrademark object : input) {
				output.add(toDTO(object));
			}
		}

		return output;

	}
}
