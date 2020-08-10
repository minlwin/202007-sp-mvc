package com.jdc.online.model.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;

@Data
@Entity
public class OnlineClass implements Serializable{

	private static final long serialVersionUID = 1L;

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
	@Column(columnDefinition = "integer default 0")
	private Status status;
	
	@OneToMany(mappedBy = "classRoom")
	private List<Registration> registrations;
	
	public enum Status {
		Available, Fixed, Finished
	}
	
	public void setCourse(Course course) {
		this.course = course;
		this.fees = course.getFees();
		this.duration = course.getDuration();
	}
	
	public String getName() {
		return String.format("%s intake %s Online Class", startDate.getMonth(), course.getName());
	}
	
}
