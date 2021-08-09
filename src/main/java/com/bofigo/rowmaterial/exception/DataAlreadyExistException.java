package com.bofigo.rowmaterial.exception;

public class DataAlreadyExistException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8148938063743494188L;

	public DataAlreadyExistException(String errorMessage) {
		super(errorMessage);
	}
}