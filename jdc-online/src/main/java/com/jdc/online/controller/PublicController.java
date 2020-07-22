package com.jdc.online.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jdc.online.model.dto.Application;
import com.jdc.online.model.dto.LoginDTO;
import com.jdc.online.service.ClassesService;
import com.jdc.online.service.RegistrationService;

@Controller
@RequestMapping("public")
public class PublicController {
	
	@Autowired
	private ClassesService clsService;
	@Autowired
	private RegistrationService regService;
	
	@GetMapping
	public String index(ModelMap model) {
		
		model.put("classes", clsService.getApplyableClasses());
		
		return "/views/public/home";
	}
	
	@GetMapping("{id}")
	public String showClassInfo(@PathVariable int id, ModelMap model) {
		model.put("classRoom", clsService.findById(id));
		return "/views/public/class-details";
	}
	
	@GetMapping("{id}/apply")
	public String apply(@PathVariable int id, ModelMap model) {
		
		// class room object
		model.put("classRoom", clsService.findById(id));

		// form object
		model.put("form", new Application());
		
		
		return "/views/public/apply-class";
	}

	@PostMapping("{id}/apply")
	public String apply(@PathVariable int id, @ModelAttribute Application form) {
		
		// validate
		
		// sign up form
		// create registration for class
		int registrationId  = regService.apply(id, form);
		
		return String.format("redirect:/public/%d/apply/%d", id, registrationId);
	}
	
	@GetMapping("{id}/apply/{registration}")
	public String showResult(@PathVariable int id, @PathVariable int registration, ModelMap model) {
		
		// class room object
		model.put("classRoom", clsService.findById(id));
		
		model.put("registration", registration);
		
		return "/views/public/apply-class";
	}
	
	@ModelAttribute("login")
	public LoginDTO login() {
		return new LoginDTO();
	}

}
