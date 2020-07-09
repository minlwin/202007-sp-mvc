package com.jdc.online.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("public")
public class PublicController {
	
	@GetMapping
	public String index() {
		
		return "/views/public/home";
	}

}
