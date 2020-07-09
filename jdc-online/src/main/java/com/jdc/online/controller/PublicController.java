package com.jdc.online.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jdc.online.service.ClassesService;

@Controller
@RequestMapping("public")
public class PublicController {
	
	@Autowired
	private ClassesService clsService;
	
	@GetMapping
	public String index(ModelMap model) {
		
		model.put("classes", clsService.getApplyableClasses());
		
		return "/views/public/home";
	}

}
