package com.bofigo.rowmaterial.exception;

public class DataNotFoundException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5697353244029942477L;

	public DataNotFoundException(String errorMessage) {
		super(errorMessage);
	}
}