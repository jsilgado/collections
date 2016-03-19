package com.jsilgado.collections.dto;

import java.util.List;

public class CarDTO {

	private String id;

	private CarTrademarkDTO carTrademarkDTO;

	private Integer year;

	private CarBrandDTO carBrandDTO;

	private String model;

	private List<String> lstIdImage;

	public CarDTO() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public CarTrademarkDTO getCarTrademarkDTO() {
		return carTrademarkDTO;
	}

	public void setCarTrademarkDTO(CarTrademarkDTO carTrademarkDTO) {
		this.carTrademarkDTO = carTrademarkDTO;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public CarBrandDTO getCarBrandDTO() {
		return carBrandDTO;
	}

	public void setCarBrandDTO(CarBrandDTO carBrandDTO) {
		this.carBrandDTO = carBrandDTO;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public List<String> getLstIdImage() {
		return lstIdImage;
	}

	public void setLstIdImage(List<String> lstIdImage) {
		this.lstIdImage = lstIdImage;
	}

}