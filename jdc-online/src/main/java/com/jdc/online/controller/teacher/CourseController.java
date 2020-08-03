package com.jdc.online.controller.teacher;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jdc.online.model.entity.Contents;
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
		return "/views/teacher/course-edit";
	}
	
	
	@GetMapping("{id}")
	public String courseDetails(@PathVariable int id, ModelMap model) {
		Course c = service.findById(id);
		model.put("course", c);
		model.put("content", new Contents());
		return "/views/teacher/course-details";
	}

	
	@PostMapping
	public String create(@ModelAttribute("data") Course course) {
		course = service.create(course);
		return "redirect:/teacher/courses/" + course.getId();
	}
	
	@PostMapping("{id}/contents")
	public String createContent(@PathVariable int id, @ModelAttribute Contents contents) {
		service.addContent(id, contents);
		return "redirect:/teacher/courses/" + id;
	}
	
	@ModelAttribute(name = "page")
	public String page() {
		return "courses";
	}
	
	@ModelAttribute(name = "data")
	public Course course(@RequestParam(required = false) Integer id) {
		return (null == id || id  == 0) ? new Course() : service.findById(id);
	}

}
