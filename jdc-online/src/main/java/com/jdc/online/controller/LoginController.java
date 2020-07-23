package com.jdc.online.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
	public String login(@ModelAttribute("login") LoginDTO login, ModelMap model) {
		
		try {
			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(login.getLoginId(), login.getPassword());
			Authentication auth = authManager.authenticate(token);
			SecurityContextHolder.getContext().setAuthentication(auth);
			
			for(GrantedAuthority authority : auth.getAuthorities()) {
				return String.format("redirect:/%s", authority.getAuthority().toLowerCase());
			}
			
		} catch (InternalAuthenticationServiceException e) {
			model.put("message", e.getMessage());
		} catch (BadCredentialsException e) {
			model.put("message", "Please check your password.");
		} catch (Exception e) {
			e.printStackTrace();
			model.put("message", e.getMessage());
		}
		
		return "/views/public/error";
	}
}
