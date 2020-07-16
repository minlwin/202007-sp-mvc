package com.jdc.online.config.model.entity;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Course {

	public enum Level {
		Basic, Intermediate, Advance
	}
	
	private int id;
	private String name;
	private Level level;
	private int duration;
	private int fees;
	private String description;
	
	private List<Contents> contents = new ArrayList<>();
}
