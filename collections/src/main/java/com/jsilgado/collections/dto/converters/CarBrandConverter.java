package com.jsilgado.collections.dto.converters;

import java.util.ArrayList;
import java.util.List;

import com.jsilgado.collections.bean.CarBrandBean;
import com.jsilgado.collections.dto.CarBrandDTO;

public class CarBrandConverter {

	private CarBrandConverter() {
	}

	public static CarBrandBean toBean(CarBrandDTO input) {

		CarBrandBean output = new CarBrandBean();

		if (input != null) {
			output.setId(input.getId());
			output.setName(input.getName());
			output.setIdImage(input.getIdImage());
		}

		return output;

	}

	public static List<CarBrandBean> toBean(List<CarBrandDTO> input) {

		List<CarBrandBean> output = null;

		if (input != null) {
			output = new ArrayList<>();

			for (CarBrandDTO object : input) {
				output.add(toBean(object));
			}
		}

		return output;

	}

	public static CarBrandDTO toDTO(CarBrandBean input) {

		CarBrandDTO output = null;

		if (input != null) {
			output = new CarBrandDTO();
			output.setId(input.getId());
			output.setName(input.getName());
			output.setIdImage(input.getIdImage());
		}

		return output;

	}

	public static List<CarBrandDTO> toDTO(List<CarBrandBean> input) {

		List<CarBrandDTO> output = null;

		if (input != null) {
			output = new ArrayList<>();

			for (CarBrandBean object : input) {
				output.add(toDTO(object));
			}
		}

		return output;

	}

}
