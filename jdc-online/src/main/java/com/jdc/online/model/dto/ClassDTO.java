package com.jdc.online.model.dto;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.jdc.online.model.entity.OnlineClass;

import lombok.Data;

@Data
public class ClassDTO {

	private int id;
	
	private String code;
	private String teacher;
	private String course;
	
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate startDate;
	private String days;
	private String times;
	private int fees;
	private int duration;
	private String requirements;
	
	public ClassDTO() {}
	
	public ClassDTO(OnlineClass c) {
		this.id = c.getId();
		this.code = c.getCourse().getCode();
		this.startDate = c.getStartDate();
		this.days = c.getDays();
		this.times = c.getTimes();
		this.fees = c.getFees();
		this.duration = c.getDuration();
		this.requirements = c.getRequirements();
		
		this.teacher = c.getCourse().getTeacher().getName();
		this.course = c.getCourse().getName();
	}
}
