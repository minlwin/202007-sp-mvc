package com.jdc.online.controller.teacher;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("teacher/courses")
public class CourseController {

	@GetMapping
	public String index() {
		
		return "/views/teacher/course-list";
	}
}
