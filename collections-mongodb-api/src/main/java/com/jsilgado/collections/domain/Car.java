package com.jsilgado.collections.domain;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "car")
public class Car {

	@Id
	private String id;

	private CarTrademark trademark;

	private CarBrand brand;

	private Integer year;

	private String model;

	private String manufacturingNumber;

	private Integer rating;

	private List<String> lstIdImage;

	public Car() {
		super();
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public CarTrademark getTrademark() {
		return this.trademark;
	}

	public void setTrademark(CarTrademark trademark) {
		this.trademark = trademark;
	}

	public Integer getYear() {
		return this.year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public CarBrand getBrand() {
		return this.brand;
	}

	public void setBrand(CarBrand brand) {
		this.brand = brand;
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
