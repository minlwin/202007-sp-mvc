package com.jdc.online.model.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
public class OnlineClass {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	private Course course;
	
	@ManyToOne
	private Account teacher;
	
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
