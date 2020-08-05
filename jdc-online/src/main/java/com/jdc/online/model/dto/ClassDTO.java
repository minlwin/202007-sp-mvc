package com.jdc.online.model.dto;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.Data;

@Data
public class ClassDTO {

	private int id;
	
	private String code;
	
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate startDate;
	private String days;
	private String times;
	private int fees;
	private int duration;
	private String requirements;
	
}
