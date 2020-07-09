package com.jdc.online.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.jdc.online.config.model.entity.OnlineClass;

@Service
public class ClassesService {

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

}
