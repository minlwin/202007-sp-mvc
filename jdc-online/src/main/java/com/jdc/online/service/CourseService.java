package com.jdc.online.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.jdc.online.model.entity.Account;
import com.jdc.online.model.entity.Account.Role;
import com.jdc.online.model.entity.Contents;
import com.jdc.online.model.entity.Course;
import com.jdc.online.model.repo.CourseRepo;
import com.jdc.online.utills.CourseCodeGenerater;
import com.jdc.online.utills.SecurityHelper;

@Service
public class CourseService {
	
	@Autowired
	private CourseRepo repo;
	@Autowired
	private AccountService accounts;
	
	@Autowired
	private SecurityHelper security;
	
	@Autowired
	private CourseCodeGenerater codeGen;

	public List<Course> search(String code, String name) {
		
		StringBuffer sb = new StringBuffer("select c from Course c where 1 = 1");
		Map<String, Object> params = new HashMap<String, Object>();

		if(security.isUserInRole(Role.Teacher)) {
			sb.append(" and c.teacher.email = :login");
			params.put("login", security.getLoginId());
		}
		
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

	@Transactional
	public Course create(Course course) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String loginUser = auth.getName();
		
		Account teacher = accounts.findByLoiginId(loginUser);
		course.setTeacher(teacher);
		
		if(StringUtils.isEmpty(course.getCode())) {
			course.setCode(codeGen.generate(course.getLevel()));
		}
		
		return repo.save(course);
	}

	@Transactional
	public void addContent(int id, Contents contents) {
		repo.findById(id).ifPresent(c -> c.getContents().add(contents));
	}

}
