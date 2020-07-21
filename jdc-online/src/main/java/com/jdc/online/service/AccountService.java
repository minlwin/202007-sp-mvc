package com.jdc.online.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jdc.online.model.entity.Account;

@Service
public class AccountService {

	public List<Account> search(String role, String name) {
		System.out.println("Role is " + role);
		System.out.println("Name is " + name);
		return null;
	}

}
