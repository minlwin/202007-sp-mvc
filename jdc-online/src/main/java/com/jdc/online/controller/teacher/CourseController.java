package com.jdc.online.controller.teacher;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("teacher/courses")
public class CourseController {

	@GetMapping
	public String index() {
		
		return "/views/teacher/course-list";
	}
	
	@GetMapping("edit")
	public String edit(@RequestParam(required = false) Integer id) {
		
		return "/views/teacher/course-edit";
	}
	
	@ModelAttribute(name = "page")
	public String page() {
		return "courses";
	}

}
