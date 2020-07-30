package com.jdc.online.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.jdc.online.model.entity.Contents;
import com.jdc.online.model.entity.Course;
import com.jdc.online.model.repo.CourseRepo;

@Service
public class CourseService {
	
	@Autowired
	private CourseRepo repo;

	public List<Course> search(String code, String name) {
		
		StringBuffer sb = new StringBuffer("select c from Course c where 1 = 1");
		
		Map<String, Object> params = new HashMap<String, Object>();
		
		if(!StringUtils.isEmpty(code)) {
			sb.append(" and lower(c.code) like lower(:code)");
			params.put("code", code.concat("%"));
		}
		
		if(!StringUtils.isEmpty(name)) {
			sb.append(" and lower(c.name) like lower(:name)");
			params.put("name", name.concat("%"));
		}
		
		return repo.search(sb.toString(), params);
	}

	public Course findById(int id) {
		return repo.findById(id).orElseThrow();
	}

	public Course create(Course course) {
		return repo.save(course);
	}

	@Transactional
	public void addContent(int id, Contents contents) {
		repo.findById(id).ifPresent(c -> c.getContents().add(contents));
	}

}
