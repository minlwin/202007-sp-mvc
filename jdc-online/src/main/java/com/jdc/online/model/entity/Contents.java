package com.jdc.online.model.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class Contents implements Serializable{

	private static final long serialVersionUID = 1L;

	private String title;
	private String details;

	public Contents() {

	}

	public Contents(String title, String details) {
		super();
		this.title = title;
		this.details = details;
	}

}
