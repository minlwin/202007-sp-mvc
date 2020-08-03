package com.jdc.online.utills;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.jdc.online.Utils;
import com.jdc.online.model.entity.Course.Level;
import com.jdc.online.model.repo.CourseRepo;

@Utils
public class CourseCodeGenerater {

	// Level (B, I, A) & Teacher ID (2digit) & Serial (2digit)
	@Value("${app.course.code.format}")
	private String format;
	
	@Autowired
	private SecurityHelper security;
	
	@Autowired
	private CourseRepo repo;
	
	public String generate(Level level) {
		
		// Level
		String l = level.name().substring(0, 1).toUpperCase();
		
		// Teacher ID
		int loginUserId = security.findLoginUserId();
		
		// Serial Number of course for Teacher
		int serial = getSerialForTeacher(loginUserId, level);
		
		return String.format(format, l, loginUserId, serial);
	}

	@SuppressWarnings("serial")
	private int getSerialForTeacher(int loginUserId, Level level) {
		return repo.count("select count (c) from Course c where c.level = :level and c.teacher.id = :teacher", 
				new HashMap<String, Object>() {
			{
				put("level", level);
				put("teacher", loginUserId);
			}
		}).intValue() + 1;
	}
	
	
}
