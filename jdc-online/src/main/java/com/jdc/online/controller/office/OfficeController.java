package com.jdc.online.controller.office;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("office")
public class OfficeController {

	@GetMapping
	public String index() {
		return  "/views/office/home";
	}
}
