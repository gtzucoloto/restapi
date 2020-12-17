package com.gabriel.resource.exception.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.gabriel.resource.exception.ProductNotFoundException;

@ControllerAdvice
public class ProductNotFoundAdvice {
	
	@ResponseBody
	@ExceptionHandler(ProductNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String productNotFoundHandler(ProductNotFoundException ex) {
		return ex.getMessage();
	}
}
