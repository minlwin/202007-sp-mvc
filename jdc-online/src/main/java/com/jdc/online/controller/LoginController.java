package com.jdc.online.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jdc.online.model.dto.LoginDTO;

@Controller
@RequestMapping("login")
public class LoginController {
	
	@Autowired
	private AuthenticationManager authManager;

	@PostMapping
	public String login(@ModelAttribute("login") LoginDTO login) {
		
		try {
			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(login.getLoginId(), login.getPassword());
			Authentication auth = authManager.authenticate(token);
			SecurityContextHolder.getContext().setAuthentication(auth);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/office";
	}
}
