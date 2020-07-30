package com.jdc.online.controller.office;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jdc.online.model.entity.Account;
import com.jdc.online.service.AccountService;

@Controller
@RequestMapping("office/members")
public class AccountController {
	
	@Autowired
	private AccountService service;
	
	@Autowired
	private PasswordEncoder encode;

	@GetMapping
	public String search(
			@RequestParam(required = false) String status, 
			@RequestParam(required = false) String role, 
			@RequestParam(required = false) String name,
			ModelMap model
	) {
		
		
		List<Account> list = service.search(status, role, name);	
		
		model.put("status", status);
		model.put("role", role);
		model.put("name", name);
		model.put("list", list);
		
		return "/views/office/account-list";
	}
	
	@GetMapping("{id}/activate")
	public String changeActiveState(@PathVariable int id, ModelMap model) {
		
		service.changeState(id);
		
		return "redirect:/office/members";
	}
	
	@PostMapping
	public String create(@ModelAttribute(name = "account") Account account) {
		
		account.setPassword(encode.encode("pass"));
		
		service.save(account);
		
		return "redirect:/office/members";
	}
	
	
	@ModelAttribute(name = "account")
	public Account account() {
		return new Account();
	}

	
	@ModelAttribute(name = "page")
	public String page() {
		return "members";
	}
}
