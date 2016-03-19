package com.jsilgado.collections.bean;

import java.io.Serializable;

import org.springframework.stereotype.Component;

@Component
public class CarTrademarkBean implements Serializable {

	private static final long serialVersionUID = -5073272148197285245L;

	private String id;

	private String name;

	private String idImage;

	private String urlImage;

	public CarTrademarkBean() {
		super();
	}

	public CarTrademarkBean(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdImage() {
		return idImage;
	}

	public void setIdImage(String idImage) {
		this.idImage = idImage;
	}

	public String getUrlImage() {
		return urlImage;
	}

	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}

}
