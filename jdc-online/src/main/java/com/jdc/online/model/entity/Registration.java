package com.jdc.online.model.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.Data;

@Data
@Entity
public class Registration implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	private OnlineClass classRoom;
	@ManyToOne
	private Account student;

	private Status status;
	
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate applyDate;
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate statusChange;
	private String remark;
	
	public enum Status {
		Apply, Paid, Attend, Cancel
	}
}
