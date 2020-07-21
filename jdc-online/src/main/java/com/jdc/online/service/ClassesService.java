package com.jdc.online.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.jdc.online.model.entity.Contents;
import com.jdc.online.model.entity.Course;
import com.jdc.online.model.entity.OnlineClass;
import com.jdc.online.model.entity.Course.Level;

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
	
	public OnlineClass findById(int id) {
		OnlineClass result = new OnlineClass();
		
		Course course = new Course();
		course.setId(1);
		course.setFees(180000);
		course.setDuration(3);
		course.setLevel(Level.Basic);
		course.setDescription("Collections are heterogeneous groups of components which are usually found together. They describe a list of \"usual suspects\" which appear in a certain context. They may include and extend other ui elements for use in certain contexts—for example form may extend dropdown or input—as well as include their own content.");
		course.setName("Java Basic");
		
		course.getContents().add(new Contents("Language Fundamentals", "Basic Java Language Semantics."));
		course.getContents().add(new Contents("OOP", "Object Oriented Programming Concepts"));
		course.getContents().add(new Contents("Essential Java API", "Basic Java SE API such  as Exceptions, IO, Collections, Streams API ..."));
		course.getContents().add(new Contents("Database", "MySQL and JDBC API."));
		course.getContents().add(new Contents("Java FX", "Java Rich Client GUI API."));
		course.getContents().add(new Contents("Final Project", "GUI Application using all above."));
		
		result.setCourse(course);
		result.setDays("Mon to Fri");
		result.setTimes("18:00 to 20:00");
		result.setStartDate(LocalDate.now());
		result.setRequirements("Basic Computer Concepts");
		
		return result;
	}

	public List<OnlineClass> search(String course, String teacher, LocalDate froms) {
		return new ArrayList<>();
	}

}
