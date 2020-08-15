package com.jdc.online.controller.office;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jdc.online.model.entity.Registration;
import com.jdc.online.model.entity.Registration.Status;
import com.jdc.online.service.RegistrationService;

@Controller
@RequestMapping("office/registrations")
public class RegistrationController {
	
	@Autowired
	private RegistrationService service;

	@GetMapping
	public String search(
			@RequestParam(required = false) String status, 
			@RequestParam(required = false) String course,
			@RequestParam(required = false) String student,
			ModelMap model
	) {
		
		List<Registration> list = service.search(status, course, student);
		model.put("list", list);
		return "/views/office/registration-list";
	}
	
	@ModelAttribute(name = "page")
	public String page() {
		return "registration";
	}
	
	@ModelAttribute(name = "statuses")
	public List<String> statuses() {
		return Arrays.stream(Status.values()).map(a -> a.name()).collect(Collectors.toList());
	}

}
