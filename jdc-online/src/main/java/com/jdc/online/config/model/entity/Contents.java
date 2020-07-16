package com.jdc.online.config.model.entity;

import lombok.Data;

@Data
public class Contents {

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
