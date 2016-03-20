package com.jsilgado.collections.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.primefaces.model.UploadedFile;
import org.springframework.stereotype.Component;

@Component
public class CarBean implements Serializable {

	private static final long serialVersionUID = -4362612768657005749L;

	private String id;

	private CarTrademarkBean trademark;

	private Integer year;

	private CarBrandBean brand;

	private String model;

	private List<String> lstIdImage;

	private List<String> lstUrlImage;

	private List<UploadedFile> lstFiles;

	public CarBean() {
		super();
		this.trademark = new CarTrademarkBean();
		this.brand = new CarBrandBean();
		this.lstFiles = new ArrayList<>();
		this.lstIdImage = new ArrayList<>();
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

	public List<String> getLstIdImage() {
		return this.lstIdImage;
	}

	public void setLstIdImage(List<String> lstIdImage) {
		this.lstIdImage = lstIdImage;
	}

	public List<UploadedFile> getLstFiles() {
		return this.lstFiles;
	}

	public void setLstFiles(List<UploadedFile> lstFiles) {
		this.lstFiles = lstFiles;
	}

	public List<String> getLstUrlImage() {
		return this.lstUrlImage;
	}

	public void setLstUrlImage(List<String> lstUrlImage) {
		this.lstUrlImage = lstUrlImage;
	}

}
