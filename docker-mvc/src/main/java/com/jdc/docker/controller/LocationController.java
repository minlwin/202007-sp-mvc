package com.jdc.docker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jdc.docker.model.entity.Division;
import com.jdc.docker.model.entity.Township;
import com.jdc.docker.model.service.LocationService;

@Controller
@RequestMapping("/locations")
public class LocationController {

	@Autowired
	private LocationService service;
	
	@GetMapping
	public String index(@RequestParam(name = "d", required = false) Integer division, ModelMap model) {
		List<Township> townships = service.findTownships(division);
		model.put("townships", townships);
		return "locations";
	}
	
	@ModelAttribute("divisions")
	public List<Division> divisions() {
		return service.getAllDivisions();
	}
}
