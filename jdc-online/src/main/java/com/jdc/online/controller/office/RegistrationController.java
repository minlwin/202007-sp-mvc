package com.jdc.online.controller.office;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("office/registrations")
public class RegistrationController {

	@GetMapping
	public String search() {
		return "/views/office/registration-list";
	}
}
