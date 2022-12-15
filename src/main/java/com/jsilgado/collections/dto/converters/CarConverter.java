package com.jsilgado.collections.dto.converters;

import java.util.ArrayList;
import java.util.List;

import com.jsilgado.collections.bean.CarBean;
import com.jsilgado.collections.bean.ImageBean;
import com.jsilgado.collections.dto.CarDTO;

public class CarConverter {

	private CarConverter() {
	}

	public static CarBean toBean(CarDTO input) {

		CarBean output = null;

		if (input != null) {
			output = new CarBean();

			output.setId(input.getId());
			output.setTrademark(CarTrademarkConverter.toBean(input.getCarTrademarkDTO()));
			output.setBrand(CarBrandConverter.toBean(input.getCarBrandDTO()));
			output.setModel(input.getModel());
			output.setYear(input.getYear());
			output.setManufacturingNumber(input.getManufacturingNumber());
			output.setRating(input.getRating());
			if (input.getLstIdImage() != null) {
				for (String idImage : input.getLstIdImage()) {
					output.getLstImagenBean().add(new ImageBean(idImage));
				}
			}
		}

		return output;

	}

	public static List<CarBean> toBean(List<CarDTO> input) {

		List<CarBean> output = null;

		if (input != null) {
			output = new ArrayList<>();

			for (CarDTO object : input) {
				output.add(toBean(object));
			}
		}

		return output;

	}

	public static CarDTO toDTO(CarBean input) {

		CarDTO output = null;

		if (input != null) {
			output = new CarDTO();

			output.setId(input.getId());
			output.setCarTrademarkDTO(CarTrademarkConverter.toDTO(input.getTrademark()));
			output.setCarBrandDTO(CarBrandConverter.toDTO(input.getBrand()));
			output.setModel(input.getModel());
			output.setYear(input.getYear());
			output.setManufacturingNumber(input.getManufacturingNumber());
			output.setRating(input.getRating());
			if (input.getLstImagenBean() != null) {
				for (ImageBean imageBean : input.getLstImagenBean()) {
					output.getLstIdImage().add(imageBean.getId());
				}
			}

		}

		return output;

	}

	public static List<CarDTO> toDTO(List<CarBean> input) {

		List<CarDTO> output = null;

		if (input != null) {
			output = new ArrayList<>();

			for (CarBean object : input) {
				output.add(toDTO(object));
			}
		}

		return output;

	}
}
