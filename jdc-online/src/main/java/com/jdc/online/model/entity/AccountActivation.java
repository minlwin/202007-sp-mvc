package com.jdc.online.model.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Data;

@Data
@Entity
public class AccountActivation {

	@Id
	private String email;
	private String code;
	private LocalDate creation;
	
	@OneToOne
	private Account account;
}
