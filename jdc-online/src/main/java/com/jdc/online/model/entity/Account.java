package com.jdc.online.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Account implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private Role role;
	private String name;
	
	@Column(unique = true, nullable = false)
	private String email;
	@Column(nullable = false)
	private String password;
	private String phone;
	
	private boolean deleted;
	
	
	public enum Role {
		Office, Teacher, Student
	}
}
