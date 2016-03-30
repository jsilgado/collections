package com.jsilgado.collections.dto.converters;

import java.util.ArrayList;
import java.util.List;

import com.jsilgado.collections.domain.Car;
import com.jsilgado.collections.dto.CarDTO;

public class CarConverter {

	public static Car toEntity(CarDTO input) {

		Car output = null;

		if (input != null) {
			output = new Car();

			output.setId(input.getId());
			output.setBrand(CarBrandConverter.toEntity(input.getCarBrandDTO()));
			output.setModel(input.getModel());
			output.setYear(input.getYear());
			output.setTrademark(CarTrademarkConverter.toEntity(input.getCarTrademarkDTO()));
			output.setLstIdImage(input.getLstIdImage());
			output.setManufacturingNumber(input.getManufacturingNumber());
			output.setRating(input.getRating());
		}

		return output;

	}

	public static CarDTO toDTO(Car input) {

		CarDTO output = null;

		if (input != null) {
			output = new CarDTO();

			output.setId(input.getId());
			output.setCarBrandDTO(CarBrandConverter.toDTO(input.getBrand()));
			output.setModel(input.getModel());
			output.setYear(input.getYear());
			output.setCarTrademarkDTO(CarTrademarkConverter.toDTO(input.getTrademark()));
			output.setLstIdImage(input.getLstIdImage());
			output.setManufacturingNumber(input.getManufacturingNumber());
			output.setRating(input.getRating());
		}

		return output;

	}

	public static List<CarDTO> toDTO(List<Car> input) {

		List<CarDTO> output = null;

		if (input != null) {
			output = new ArrayList<CarDTO>();

			for (Car object : input) {
				output.add(toDTO(object));
			}
		}

		return output;

	}
}
