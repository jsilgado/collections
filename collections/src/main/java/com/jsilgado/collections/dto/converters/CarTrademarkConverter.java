package com.jsilgado.collections.dto.converters;

import java.util.ArrayList;
import java.util.List;

import com.jsilgado.collections.bean.CarTrademarkBean;
import com.jsilgado.collections.dto.CarTrademarkDTO;

public class CarTrademarkConverter {

	private CarTrademarkConverter() {
	}

	public static CarTrademarkBean toBean( CarTrademarkDTO input ) {

		CarTrademarkBean output = null;

		if (input != null) {
			output = new CarTrademarkBean();
			output.setId(input.getId());
			output.setName(input.getName());
			output.setIdImage(input.getIdImage());
		}

		return output;

	}

	public static List<CarTrademarkBean> toBean ( List<CarTrademarkDTO> input ) {

		List<CarTrademarkBean> output = null;

		if (input != null) {
			output = new ArrayList<>();

			for (CarTrademarkDTO object : input) {
				output.add(toBean(object));
			}
		}

		return output;

	}

	public static CarTrademarkDTO toDTO( CarTrademarkBean input ) {

		CarTrademarkDTO output = null;

		if (input != null) {
			output = new CarTrademarkDTO();
			output.setId(input.getId());
			output.setName(input.getName());
			output.setIdImage(input.getIdImage());
		}

		return output;

	}

	public static List<CarTrademarkDTO> toDTO ( List<CarTrademarkBean> input ) {

		List<CarTrademarkDTO> output = null;

		if (input != null) {
			output = new ArrayList<>();

			for (CarTrademarkBean object : input) {
				output.add(toDTO(object));
			}
		}

		return output;

	}
}
