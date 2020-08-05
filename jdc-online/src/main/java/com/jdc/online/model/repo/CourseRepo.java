package com.jdc.online.model.repo;

import java.util.List;

import com.jdc.online.model.BaseRepository;
import com.jdc.online.model.entity.Course;

public interface CourseRepo extends BaseRepository<Course, Integer> {

	List<Course> findByTeacherId(int id);
	
	Course findByCode(String code);
	
}
