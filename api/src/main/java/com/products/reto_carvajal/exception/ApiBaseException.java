package com.products.reto_carvajal.exception;

import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;

public class ApiBaseException {

	private final String message;
	
	private final HttpStatus httpStatus;
	
	private final int errorCode;
	
	private final ZonedDateTime timestamp;

	public String getMessage() {
		return message;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public ZonedDateTime getTimestamp() {
		return timestamp;
	}

	/**
	 * Constructor de la excepcion
	 * 
	 * @param message
	 * @param httpStatus
	 * @param timestamp
	 */
	public ApiBaseException(String message, HttpStatus httpStatus, int errorCode, ZonedDateTime timestamp) {
		this.message = message;
		this.httpStatus = httpStatus;
		this.errorCode = errorCode;
		this.timestamp = timestamp;
	}

}
