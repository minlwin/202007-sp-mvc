package com.jdc.online.service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.jdc.online.model.dto.ClassDTO;
import com.jdc.online.model.dto.ClassForPublic;
import com.jdc.online.model.dto.ClassForTeacher;
import com.jdc.online.model.entity.Course;
import com.jdc.online.model.entity.OnlineClass;
import com.jdc.online.model.entity.Registration;
import com.jdc.online.model.entity.Registration.Status;
import com.jdc.online.model.repo.OnlineClassRepo;

@Service
public class ClassesService {
	
	@Autowired
	private OnlineClassRepo repo;
	
	@Autowired
	private CourseService courses;
	

	@SuppressWarnings("serial")
	public List<ClassForPublic> getApplyableClasses() {
		
		StringBuffer sb = new StringBuffer("select new com.jdc.online.model.dto.ClassForPublic(c, count(r)) from OnlineClass c left outer join c.registrations r where c.status = :status group by c.id order by c.startDate");
		Map<String, Object> params = new HashMap<>() {
			{
				put("status", OnlineClass.Status.Available);
			}
		};
		
		return repo.search(sb.toString(), params, ClassForPublic.class);		
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
		
		if(entity.getId() == 0) {
			entity.setStatus(OnlineClass.Status.Available);
		}
		
		Course course = courses.findByCode(dto.getCode());
		entity.setCourse(course);
		entity.setTeacher(course.getTeacher());
	}

	@SuppressWarnings("serial")
	public ClassDTO findDtoById(int id) {
		return repo.findOneDto("select new com.jdc.online.model.dto.ClassDTO(c) from OnlineClass c where id = :id", 
				new HashMap<String, Object>() {{put("id", id);}}, 
				ClassDTO.class);
	}

	@Transactional
	public List<ClassForTeacher> searchForTeacher(String course, LocalDate from, LocalDate to) {
		
		String teacher = SecurityContextHolder.getContext().getAuthentication().getName();
		
		StringBuffer sb = new StringBuffer("select c from OnlineClass c where c.teacher.email = :teacher");
		Map<String, Object> params = new HashMap<>();
		params.put("teacher", teacher);
		
		if(!StringUtils.isEmpty(course)) {
			sb.append(" and (lower(c.course.code) like lower(:course) or lower(c.course.name) like lower(:course))");
			params.put("course", course.concat("%"));
		}
		
		if(null != from) {
			sb.append(" and c.startDate >= :from");
			params.put("from", from);
		}
		
		if(null != to) {
			sb.append(" and c.startDate <= :to");
			params.put("to", to);
		}
		
		return repo.search(sb.toString(), params).stream().map(c -> {
			ClassForTeacher dto = new ClassForTeacher(c);
			dto.setPending(getCount(c.getRegistrations(), r -> r.getStatus() == Status.Apply || r.getStatus() == Status.Paid));
			dto.setAttend(getCount(c.getRegistrations(), r -> r.getStatus() == Status.Attend));
			return dto;
		}).collect(Collectors.toList());
	}
	
	private int getCount(List<Registration> list, Predicate<Registration> filter) {
		Long count = list.stream().filter(filter).count();
		return count.intValue();
	}

}
