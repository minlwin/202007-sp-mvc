package com.jdc.online.controller.office;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jdc.online.config.model.entity.OnlineClass;
import com.jdc.online.service.ClassesService;

@Controller("OfficeClasses")
@RequestMapping("office/classes")
public class ClassController {
	
	@Autowired
	private ClassesService service;

	@GetMapping
	public String search(
			@RequestParam(required = false) String course,
			@RequestParam(required = false) String teacher,
			@DateTimeFormat(iso = ISO.DATE)
			@RequestParam(required = false) LocalDate from,
			@DateTimeFormat(iso = ISO.DATE)
			@RequestParam(required = false) LocalDate to,
			ModelMap model
			) {
		
		List<OnlineClass> list = service.search(course, teacher, from, to);
		model.put("list", list);
		
		return "/views/office/class-list";
	}
}
