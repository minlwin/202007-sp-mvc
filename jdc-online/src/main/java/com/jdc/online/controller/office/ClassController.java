package com.jdc.online.controller.office;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jdc.online.model.dto.ClassDTO;
import com.jdc.online.model.entity.Account;
import com.jdc.online.model.entity.Account.Role;
import com.jdc.online.model.entity.Course;
import com.jdc.online.model.entity.OnlineClass;
import com.jdc.online.service.AccountService;
import com.jdc.online.service.ClassesService;
import com.jdc.online.service.CourseService;

@Controller("OfficeClasses")
@RequestMapping("office/classes")
public class ClassController {
	
	@Autowired
	private ClassesService service;
	
	@Autowired
	private AccountService accounts;
	
	@Autowired
	private CourseService courses;

	@GetMapping
	public String search(
			@RequestParam(required = false) String course,
			@RequestParam(required = false) String teacher,
			@DateTimeFormat(iso = ISO.DATE)
			@RequestParam(required = false) LocalDate from,
			ModelMap model
			) {
		
		List<OnlineClass> list = service.search(course, teacher, from);
		model.put("list", list);
		
		List<Account> teachers = accounts.search("0", Role.Teacher.name(), null);
		model.put("teachers", teachers);
		
		if(!teachers.isEmpty()) {
			
			List<Course> courseList = courses.search(teachers.get(0).getId());
			model.put("courses", courseList);
		}
		
		model.put("dto", new ClassDTO());
		
		return "/views/office/class-list";
	}
	
	@PostMapping
	public String save(@ModelAttribute ClassDTO dto) {
		service.save(dto);
		return "redirect:/office/classes";
	}
	
	
	@ModelAttribute(name = "page")
	public String page() {
		return "classes";
	}

}
