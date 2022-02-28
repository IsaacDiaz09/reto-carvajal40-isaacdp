package com.products.reto_carvajal.response;

import org.springframework.http.HttpStatus;

public class HttpResponse {

	private int statusCode;

	private HttpStatus httpStatus;

	private String message;

	/**
	 * 
	 * @param statusCode
	 * @param httpStatus
	 * @param message
	 */
	private HttpResponse(int statusCode, HttpStatus httpStatus, String message) {
		this.statusCode = statusCode;
		this.httpStatus = httpStatus;
		this.message = message;

	}

	public static HttpResponse of(int statusCode, HttpStatus httpStatus, String message) {
		return new HttpResponse(statusCode, httpStatus, message);
	}

	public HttpResponse() {
	}

	public int getStatusCode() {
		return statusCode;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public String getMessage() {
		return message;
	}

}
