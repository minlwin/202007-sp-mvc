package com.jdc.online.model.dto;

import java.time.LocalDate;

import com.jdc.online.model.entity.OnlineClass;
import com.jdc.online.model.entity.OnlineClass.Status;

import lombok.Data;

@Data
public class ClassForTeacher {

	private int id;
	private String course;
	private LocalDate startDate;
	private String days;
	private String times;
	private Status status;
	private int attend;
	private int pending;
	
	public  ClassForTeacher() {
		
	}

	public  ClassForTeacher(OnlineClass c) {
		this.id = c.getId();
		this.course = c.getCourse().getName();
		this.startDate = c.getStartDate();
		this.days = c.getDays();
		this.times = c.getTimes();
		this.status = c.getStatus();
	}
}
