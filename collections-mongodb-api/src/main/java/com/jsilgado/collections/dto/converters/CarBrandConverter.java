package com.jsilgado.collections.dto.converters;

import java.util.ArrayList;
import java.util.List;

import com.jsilgado.collections.domain.CarBrand;
import com.jsilgado.collections.dto.CarBrandDTO;

public class CarBrandConverter {

	public static CarBrand toEntity( CarBrandDTO input ) {

		CarBrand output = null;

		if (input != null) {
			output = new CarBrand();
			output.setId(input.getId());
			output.setName(input.getName());
			output.setIdImage(input.getIdImage());
		}

		return output;

	}

	public static CarBrandDTO toDTO( CarBrand input ) {

		CarBrandDTO output = null;

		if (input != null) {
			output = new CarBrandDTO();
			output.setId(input.getId());
			output.setName(input.getName());
			output.setIdImage(input.getIdImage());
		}

		return output;

	}

	public static List<CarBrandDTO> toDTO ( List<CarBrand> input ) {

		List<CarBrandDTO> output = null;

		if (input != null) {
			output = new ArrayList<>();

			for (CarBrand object : input) {
				output.add(toDTO(object));
			}
		}

		return output;

	}
}
