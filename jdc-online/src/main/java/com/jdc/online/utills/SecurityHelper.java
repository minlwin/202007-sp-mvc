package com.jdc.online.utills;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import com.jdc.online.Utils;
import com.jdc.online.model.entity.Account;
import com.jdc.online.model.entity.Account.Role;
import com.jdc.online.model.repo.AccountRepo;

@Utils
public class SecurityHelper {
	
	@Autowired
	private AccountRepo accountRepo;

	public boolean isUserInRole(Role role) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		for(GrantedAuthority authority : auth.getAuthorities()) {
			if(authority.getAuthority().equals(role.name())) {
				return true;
			}
		}
		
		return false;
	}
	
	public String getLoginId() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return auth.getName();
	}

	public int findLoginUserId() {
		return findByEmail(getLoginId()).getId();
	}
	
	private Account findByEmail(String email) {
		Optional<Account> optional = accountRepo.findOneByEmail(email);
		return optional.orElseThrow();
	}
	
	
}
