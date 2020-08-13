package com.jdc.online.service;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jdc.online.model.repo.AccountRepo;

@Service
public class OnlineUserDetailsService implements UserDetailsService{
	
	@Autowired
	private AccountRepo repo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return repo.findOneByEmail(username)
				.map(a -> User.builder()
						.password(a.getPassword())
						.username(a.getEmail())
						.accountExpired(a.isDeleted())
						.accountLocked(!a.isActivated())
						.authorities(a.getRole().name())
						.build())
				.orElseThrow(() -> new EntityNotFoundException("Please check your login id!"));
	}

}
