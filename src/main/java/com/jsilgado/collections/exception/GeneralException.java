package com.jsilgado.collections.exception;

public class GeneralException extends Exception {
	/**
	 * Serial version.
	 */
	private static final long serialVersionUID = 6934858927066778933L;
	/**
	 * Atributo que contiene el cogido del error.
	 */
	private String codeError;
	/**
	 * Atributo que contiene la descripcion del error.
	 */
	private String description;

	public GeneralException() {

	}

	public GeneralException(String codeError, String description) {
		this.codeError = codeError;
		this.description = description;
	}

	public String getCodeError() {
		return codeError;
	}

	public void setCodeError(String codeError) {
		this.codeError = codeError;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
