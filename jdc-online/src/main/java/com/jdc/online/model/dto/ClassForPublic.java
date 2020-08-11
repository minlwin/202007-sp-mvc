package com.jdc.online.model.dto;

import java.time.LocalDate;

import com.jdc.online.model.entity.OnlineClass;

import lombok.Data;

@Data
public class ClassForPublic {

	private int id;
	private String course;
	private int fees;
	private LocalDate startDate;
	private long applications;
	
	public ClassForPublic() {}

	public ClassForPublic(OnlineClass c, long applications) {
		this.id = c.getId();
		this.course = c.getCourse().getName();
		this.fees = c.getFees();
		this.startDate = c.getStartDate();
		this.applications = applications;
	}
}
