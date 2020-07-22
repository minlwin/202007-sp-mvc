package com.jdc.online.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Course implements Serializable{

	private static final long serialVersionUID = 1L;

	public enum Level {
		Basic, Intermediate, Advance
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(unique = true, nullable = false)
	private String code;
	private String name;
	private Level level;
	private int duration;
	private int fees;
	private String requirements;
	private String description;
	
	@ElementCollection
	private List<Contents> contents = new ArrayList<>();
}
