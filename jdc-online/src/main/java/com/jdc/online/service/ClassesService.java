package com.jdc.online.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jdc.online.model.entity.OnlineClass;
import com.jdc.online.model.repo.OnlineClassRepo;

@Service
public class ClassesService {
	
	@Autowired
	private OnlineClassRepo repo;

	public List<OnlineClass> getApplyableClasses() {
		return Arrays.asList(
				new OnlineClass(),
				new OnlineClass(),
				new OnlineClass(),
				new OnlineClass(),
				new OnlineClass(),
				new OnlineClass()
		);
		
	}
	
	public OnlineClass findById(int id) {
		return repo.findById(id).orElseThrow();
	}

	public List<OnlineClass> search(String course, String teacher, LocalDate froms) {
		return new ArrayList<>();
	}

}
