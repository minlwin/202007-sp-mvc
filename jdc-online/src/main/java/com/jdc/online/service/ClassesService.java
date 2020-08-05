package com.jdc.online.service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.jdc.online.model.dto.ClassDTO;
import com.jdc.online.model.entity.Course;
import com.jdc.online.model.entity.OnlineClass;
import com.jdc.online.model.repo.OnlineClassRepo;

@Service
public class ClassesService {
	
	@Autowired
	private OnlineClassRepo repo;
	
	@Autowired
	private CourseService courses;
	

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

	public List<OnlineClass> search(String course, String teacher, LocalDate from) {
		
		StringBuffer sb = new StringBuffer("select c from OnlineClass c where 1 = 1");
		Map<String, Object> params = new HashMap<>();
		
		if(!StringUtils.isEmpty(course)) {
			sb.append(" and (lower(c.course.code) like lower(:course) or lower(c.course.name) like lower(:course))");
			params.put("course", course.concat("%"));
		}
		
		if(!StringUtils.isEmpty(teacher)) {
			sb.append(" and lower(c.teacher.name) like lower(:teacher)");
			params.put("teacher", teacher.concat("%"));
		}

		if(null != from) {
			sb.append(" and c.startDate >= :from");
			params.put("from", from);
		}
		
		
		return repo.search(sb.toString(), params);
	}

	public void save(ClassDTO dto) {
		OnlineClass entity = dto.getId() == 0 ? new OnlineClass() : repo.getOne(dto.getId());
		copy(entity, dto);
		repo.save(entity);
	}
	
	private void copy(OnlineClass entity, ClassDTO dto) {
		
		entity.setDays(dto.getDays());
		entity.setDuration(dto.getDuration());
		entity.setFees(dto.getFees());
		entity.setRequirements(dto.getRequirements());
		entity.setStartDate(dto.getStartDate());
		entity.setTimes(dto.getTimes());
		
		Course course = courses.findByCode(dto.getCode());
		entity.setCourse(course);
		entity.setTeacher(course.getTeacher());
	}

}
