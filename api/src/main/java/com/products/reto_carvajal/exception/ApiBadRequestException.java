package com.products.reto_carvajal.exception;

public class ApiBadRequestException extends RuntimeException {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -3853532292759155381L;

	/**
	 * 
	 * @param message
	 */
	public ApiBadRequestException(String message) {
		super(message);
	}

}
