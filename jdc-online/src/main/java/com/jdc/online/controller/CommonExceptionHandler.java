package com.jdc.online.controller;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CommonExceptionHandler {
	
	@ExceptionHandler(OnlineException.class)
	public void handle(OnlineException e, ModelMap model) {
		model.put("message", e.getMessage());
	}
}
