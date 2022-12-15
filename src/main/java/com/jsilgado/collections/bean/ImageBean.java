package com.jsilgado.collections.bean;

import java.io.Serializable;

import org.primefaces.model.UploadedFile;
import org.springframework.stereotype.Component;

@Component
public class ImageBean implements Serializable {

	private static final long serialVersionUID = -4362612768657005749L;

	private String id;

	private String url;

	private UploadedFile file;

	private String description;

	private Integer size;

	public ImageBean() {
		super();
	}

	public ImageBean(String id) {
		super();
		this.id = id;
	}

	public String getId() {
		return this.id;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public UploadedFile getFile() {
		return this.file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getSize() {
		return this.size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

}
