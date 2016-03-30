package com.jsilgado.collections.exception;

public class HelperException extends GeneralException {

	private static final long serialVersionUID = -2133071948635914544L;

	public HelperException() {
		super();
	}

	public HelperException(String description) {
		super("500", description);
	}

	public HelperException(String codeError, String description) {
		super(codeError, description);
	}

}
