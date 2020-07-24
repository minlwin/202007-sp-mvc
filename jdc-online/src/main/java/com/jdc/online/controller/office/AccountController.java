package com.jdc.online.controller.office;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
			@RequestParam(required = false) String role, 
			@RequestParam(required = false) String name,
			ModelMap model
	) {
		
		
		List<Account> list = service.search(role, name);		
		model.put("list", list);
		model.put("account", new Account());
		
		return "/views/office/account-list";
	}
	
	@PostMapping
	public String create(@ModelAttribute(name = "account") Account account) {
		
		account.setPassword(encode.encode("pass"));
		
		service.save(account);
		
		return "redirect:/office/members";
	}
	
	@ModelAttribute(name = "page")
	public String page() {
		return "members";
	}
}
