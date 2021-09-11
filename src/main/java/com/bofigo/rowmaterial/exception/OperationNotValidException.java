package com.bofigo.rowmaterial.exception;

public class OperationNotValidException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5697353244029942477L;

	public OperationNotValidException(String errorMessage) {
		super(errorMessage);
	}
}