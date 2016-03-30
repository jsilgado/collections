package com.jsilgado.collections.dto;

import java.util.List;

public class CarDTO {

	private String id;

	private CarTrademarkDTO carTrademarkDTO;

	private Integer year;

	private CarBrandDTO carBrandDTO;

	private String model;

	private String manufacturingNumber;

	private Integer rating;

	private List<String> lstIdImage;

	public CarDTO() {
		super();
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public CarTrademarkDTO getCarTrademarkDTO() {
		return this.carTrademarkDTO;
	}

	public void setCarTrademarkDTO(CarTrademarkDTO carTrademarkDTO) {
		this.carTrademarkDTO = carTrademarkDTO;
	}

	public Integer getYear() {
		return this.year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public CarBrandDTO getCarBrandDTO() {
		return this.carBrandDTO;
	}

	public void setCarBrandDTO(CarBrandDTO carBrandDTO) {
		this.carBrandDTO = carBrandDTO;
	}

	public String getModel() {
		return this.model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public List<String> getLstIdImage() {
		return this.lstIdImage;
	}

	public void setLstIdImage(List<String> lstIdImage) {
		this.lstIdImage = lstIdImage;
	}

	public String getManufacturingNumber() {
		return this.manufacturingNumber;
	}

	public void setManufacturingNumber(String manufacturingNumber) {
		this.manufacturingNumber = manufacturingNumber;
	}

	public Integer getRating() {
		return this.rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}
}