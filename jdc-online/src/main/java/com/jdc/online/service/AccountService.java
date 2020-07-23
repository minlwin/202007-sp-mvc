package com.jdc.online.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jdc.online.model.entity.Account;
import com.jdc.online.model.repo.AccountRepo;

@Service
public class AccountService {
	
	@Autowired
	private AccountRepo repo;

	public List<Account> search(String role, String name) {
		return null;
	}

	public void save(Account account) {
		repo.save(account);
	}

}
