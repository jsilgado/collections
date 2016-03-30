package com.jsilgado.collections.bean;

import java.io.Serializable;

import org.springframework.stereotype.Component;

@Component
public class CarTrademarkBean implements Serializable {

	private static final long serialVersionUID = -5073272148197285245L;

	private String id;

	private String name;

	private ImageBean imageBean;

	public CarTrademarkBean() {
		super();
	}

	public CarTrademarkBean(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ImageBean getImageBean() {
		return this.imageBean;
	}

	public void setImageBean(ImageBean imageBean) {
		this.imageBean = imageBean;
	}

}
