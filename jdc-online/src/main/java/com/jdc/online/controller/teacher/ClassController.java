package com.jdc.online.controller.teacher;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jdc.online.service.ClassesService;

@Controller("TeacherClasses")
@RequestMapping("teacher/classes")
public class ClassController {
	
	@Autowired
	private ClassesService service;

	@GetMapping
	public String index(
			@RequestParam(required = false) String course,
			@DateTimeFormat(iso = ISO.DATE)
			@RequestParam(required = false) LocalDate from,
			@DateTimeFormat(iso = ISO.DATE)
			@RequestParam(required = false) LocalDate to,
			ModelMap model
			) {
		
		String teacher = "";
		
		model.put("list", service.search(course, teacher, from, to));
		return "/views/teacher/class-list";
	}
}
