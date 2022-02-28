package com.products.reto_carvajal.exception;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

/**
 * Clase manejadora de excepciones
 */

@RestControllerAdvice
public class ApiExceptionHandler {

	// error 400
	private static final HttpStatus BAD_REQUEST = HttpStatus.BAD_REQUEST;

	/**
	 * @param ex
	 * @return ResponseEntity
	 */
	@ExceptionHandler(value = { ApiBadRequestException.class })
	public ResponseEntity<Object> handleApiBadRequestException(ApiBadRequestException ex) {
		ApiBaseException exception = new ApiBaseException(ex.getMessage(), BAD_REQUEST, BAD_REQUEST.value(),
				ZonedDateTime.now(ZoneId.systemDefault()));
		return new ResponseEntity<>(exception, BAD_REQUEST);
	}

	/**
	 * Excepcion generada cuando no se pudo parsear el JSON recibido en la solicitud
	 * o se envio un valor que no corresponde a los validos en las enumeraciones
	 * 
	 * @param ex
	 * @return ResponseEntity
	 */
	@ExceptionHandler(value = { HttpMessageNotReadableException.class })
	public ResponseEntity<Object> handleInvalidEnumValue(HttpMessageNotReadableException ex) {
		ApiBaseException exception = new ApiBaseException(ex.getMessage(), BAD_REQUEST, BAD_REQUEST.value(),
				ZonedDateTime.now(ZoneId.systemDefault()));
		if (ex.getCause() != null
				&& (ex.getCause() instanceof JsonParseException || ex.getCause() instanceof JsonMappingException)) {

			ApiBaseException e = new ApiBaseException(
					"Ups, no se pudo procesar la solicitud debido a un error de sintaxis en los datos recibidos",
					BAD_REQUEST, BAD_REQUEST.value(), ZonedDateTime.now(ZoneId.systemDefault()));

			return new ResponseEntity<>(e, BAD_REQUEST);
		}

		return new ResponseEntity<>(exception, BAD_REQUEST);
	}

}
