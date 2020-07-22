package com.jdc.online.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jdc.online.model.entity.Account;
import com.jdc.online.model.entity.Account.Role;
import com.jdc.online.model.repo.AccountRepo;

@Service
public class InitUserService {

	@Autowired
	private AccountRepo repo;
	
	@Autowired
	private PasswordEncoder encoder;
	
	public void addInitUsers() {
		
		if(repo.count() == 0) {
			
			// office user
			Account office = new Account();
			office.setName("Office Admin");
			office.setEmail("office");
			office.setPassword(encoder.encode("office"));
			office.setRole(Role.Office);
			office.setDeleted(false);
			
			repo.save(office);
		}
	}
}
