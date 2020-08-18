package com.jdc.online.controller.teacher;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jdc.online.model.dto.ClassForTeacher;
import com.jdc.online.model.entity.Account;
import com.jdc.online.model.entity.Registration.Status;
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
		return "views/teacher/class-list";
	}
	
	@GetMapping("{id}/students")
	public String studedents(@PathVariable int id, ModelMap model) {
		
		// Class Information
		model.put("classRoom", service.findById(id));
		
		// Registration by Status
		Map<Status, List<Account>> students = service.findStudentsForClasss(id);
		model.put("students", students);
		
		return "views/teacher/class-students";
	}
	
	@ModelAttribute(name = "page")
	public String page() {
		return "classes";
	}
}
