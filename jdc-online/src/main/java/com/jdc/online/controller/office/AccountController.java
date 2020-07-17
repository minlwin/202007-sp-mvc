package com.jdc.online.controller.office;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jdc.online.config.model.entity.Account;
import com.jdc.online.service.AccountService;

@Controller
@RequestMapping("office/members")
public class AccountController {
	
	@Autowired
	private AccountService service;

	@GetMapping
	public String search(
			@RequestParam(required = false, name = "r") String role, 
			@RequestParam(required = false, name = "n") String name,
			ModelMap model
	) {
		
		List<Account> list = service.search(role, name);		
		model.put("list", list);
		return "/views/office/account-list";
	}
}
