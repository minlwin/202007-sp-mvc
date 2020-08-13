package com.jdc.docker.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jdc.docker.model.entity.Division;
import com.jdc.docker.model.entity.Township;
import com.jdc.docker.model.repo.DivisionRepo;
import com.jdc.docker.model.repo.TownshipRepo;

@Service
public class LocationService {
	
	@Autowired
	private TownshipRepo townships;
	@Autowired
	private DivisionRepo divisions;

	public List<Division> getAllDivisions() {
		return divisions.findAll();
	}

	public List<Township> findTownships(Integer division) {
		return townships.findByDivisionId(division == null ? 1 : division);
	}

}
