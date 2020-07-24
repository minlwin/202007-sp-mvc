package com.jdc.online.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.jdc.online.model.entity.Account;
import com.jdc.online.model.entity.Account.Role;
import com.jdc.online.model.repo.AccountRepo;

@Service
public class AccountService {
	
	@Autowired
	private AccountRepo repo;

	public List<Account> search(String role, String name) {
				
		StringBuffer sb = new StringBuffer("select a from Account a where a.role <> 2 and a.deleted = false");
		Map<String, Object> params = new HashMap<String, Object>();

		Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		if(null != obj && obj instanceof User) {
			User user = (User) obj;
			sb.append(" and a.email <> :loginId");
			params.put("loginId", user.getUsername());
		}
		
		if(!StringUtils.isEmpty(role)) {
			sb.append(" and a.role = :role");
			params.put("role", Role.valueOf(role));
		}
		
		if(!StringUtils.isEmpty(name)) {
			sb.append(" and lower(a.name) like lower(:name)");
			params.put("name", name.concat("%"));
		}
 		
		return repo.search(sb.toString(), params);
	}

	public void save(Account account) {
		repo.save(account);
	}

}
