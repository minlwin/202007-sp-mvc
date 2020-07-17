package com.jdc.online.controller.office;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("office/classes")
public class ClassController {

	@GetMapping
	public String search() {
		return "/views/office/class-list";
	}
}
