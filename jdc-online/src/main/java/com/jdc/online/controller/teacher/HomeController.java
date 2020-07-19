package com.jdc.online.controller.teacher;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("TeacherHome")
@RequestMapping("teacher")
public class HomeController {

	@GetMapping
	public String index(ModelMap model) {

		// active class
		
		// new class
		
		// finished classes
		
		// courses
		
		return "/views/teacher/home";
	}
}
