package com.jdc.online.controller.teacher;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jdc.online.model.dto.ClassForTeacher;
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
		
		List<ClassForTeacher> list = service.searchForTeacher(course, from, to);
		model.put("list", list);
		return "/views/teacher/class-list";
	}
	
	@ModelAttribute(name = "page")
	public String page() {
		return "classes";
	}
}
