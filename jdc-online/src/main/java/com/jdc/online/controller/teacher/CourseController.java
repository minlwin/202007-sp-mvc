package com.jdc.online.controller.teacher;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jdc.online.model.entity.Course;
import com.jdc.online.service.CourseService;

@Controller
@RequestMapping("teacher/courses")
public class CourseController {
	
	@Autowired
	private CourseService service;

	@GetMapping
	public String index(
			@RequestParam(required = false) String code,
			@RequestParam(required = false) String name,
			ModelMap model
			) {
		
		List<Course> list = service.search(code, name);
		model.put("list", list);
		
		return "/views/teacher/course-list";
	}
	
	@GetMapping("edit")
	public String edit(@RequestParam(required = false) Integer id, Model model) {
		model.addAttribute("data", new Course());
		return "/views/teacher/course-edit";
	}
	
	@PostMapping
	public String create(@ModelAttribute("data") Course course) {
		
		return null;
	}
	
	@ModelAttribute(name = "page")
	public String page() {
		return "courses";
	}

}
