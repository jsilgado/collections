package com.jsilgado.collections.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class CarBean implements Serializable {

	private static final long serialVersionUID = -4362612768657005749L;

	private String id;

	private CarTrademarkBean trademark;

	private Integer year;

	private CarBrandBean brand;

	private String model;

	private String manufacturingNumber;

	private Integer rating;

	private List<ImageBean> lstImagenBean;

	public CarBean() {
		super();
		this.trademark = new CarTrademarkBean();
		this.brand = new CarBrandBean();
		this.lstImagenBean = new ArrayList<>();
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getYear() {
		return this.year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public CarBrandBean getBrand() {
		return this.brand;
	}

	public void setBrand(CarBrandBean brand) {
		this.brand = brand;
	}

	public String getModel() {
		return this.model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public CarTrademarkBean getTrademark() {
		return this.trademark;
	}

	public void setTrademark(CarTrademarkBean trademark) {
		this.trademark = trademark;
	}

	public List<ImageBean> getLstImagenBean() {
		return this.lstImagenBean;
	}

	public void setLstImagenBean(List<ImageBean> lstImagenBean) {
		this.lstImagenBean = lstImagenBean;
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
