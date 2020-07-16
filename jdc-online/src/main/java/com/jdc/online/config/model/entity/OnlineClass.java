package com.jdc.online.config.model.entity;

import java.time.LocalDate;

import lombok.Data;

@Data
public class OnlineClass {

	private int id;
	
	private Course course;
	private LocalDate startDate;
	private String days;
	private String times;
	private int fees;
	private int duration;
	private String requirements;
	
	public void setCourse(Course course) {
		this.course = course;
		this.fees = course.getFees();
		this.duration = course.getDuration();
	}
	
	public String getName() {
		return String.format("%s intake %s Online Class", startDate.getMonth(), course.getName());
	}
	
}
