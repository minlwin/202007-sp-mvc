package com.jdc.online.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.online.model.entity.Course;
import com.jdc.online.service.CourseService;

@RestController
@RequestMapping("public/courses")
public class CourseApi {
	
	@Autowired
	private CourseService service;
	
	@GetMapping("{code}")
	public Course findByCode(@PathVariable String code) {
		return service.findByCode(code);
	}
	
	@GetMapping("teacher/{id}")
	public List<Course> findByTeacherId(@PathVariable int id) {
		return service.findByTeacherId(id);
	}

}
