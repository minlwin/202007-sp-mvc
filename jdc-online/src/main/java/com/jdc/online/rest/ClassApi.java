package com.jdc.online.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.online.model.dto.ClassDTO;
import com.jdc.online.service.ClassesService;

@RestController
@RequestMapping("public/classes")
public class ClassApi {
	
	@Autowired
	private ClassesService service;
	
	@GetMapping("{id}")
	public ClassDTO findById(@PathVariable int id) {
		return service.findDtoById(id);
	}

}
