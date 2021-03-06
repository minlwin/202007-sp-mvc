package com.jdc.online.service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.jdc.online.model.dto.Application;
import com.jdc.online.model.entity.Account;
import com.jdc.online.model.entity.Account.Role;
import com.jdc.online.model.entity.AccountActivation;
import com.jdc.online.model.entity.Registration;
import com.jdc.online.model.entity.Registration.Status;
import com.jdc.online.model.repo.AccountRepo;
import com.jdc.online.model.repo.RegistrationRepo;

@Service
public class RegistrationService {
	
	@Autowired
	private AccountRepo accounts;
	@Autowired
	private PasswordEncoder encoder;
	@Autowired
	private RegistrationRepo registrations;
	@Autowired
	private ClassesService classRooms;
	

	@Transactional
	public int apply(int id, Application form) {
		
		// create student
		Account account = accounts.findOneByEmail(form.getEmail()).orElseGet(() -> {
			Account student = new Account();
			student.setEmail(form.getEmail());
			student.setName(form.getName());
			student.setPhone(form.getPhone());
			student.setPassword(encoder.encode("pass"));
			student.setRole(Role.Student);
			
			AccountActivation activation = new AccountActivation();
			activation.setEmail(student.getEmail());
			activation.setAccount(student);
			activation.setCreation(LocalDate.now());
			activation.setCode(encoder.encode(student.getEmail()));
			student.setActivation(activation);
			
			return student;
		});
		
		// register student to class room
		Registration registration = new Registration();
		registration.setStudent(account);
		registration.setStatus(Status.Apply);
		registration.setStatusChange(LocalDate.now());
		registration.setApplyDate(LocalDate.now());
		
		registration.setClassRoom(classRooms.findById(id));

		
		// TODO send email to student for sign in link 
		
		// create registration
		registration = registrations.save(registration);
		// return registration id
		return registration.getId();
	}


	public List<Registration> search(String status, String course, String student) {
		
		StringBuffer sb = new StringBuffer("select r from Registration r where 1 = 1");
		Map<String, Object> params = new HashMap<>();
		
		if(!StringUtils.isEmpty(status) && !"-1".equals(status)) {
			sb.append(" and r.status = :status");
			params.put("status", Status.valueOf(status));
		}
		
		if(!StringUtils.isEmpty(course)) {
			sb.append(" and (lower(r.classRoom.course.code) like lower(:course) or lower(r.classRoom.course.name) like lower(:course))");
			params.put("course", course.concat("%"));
		}

		if(!StringUtils.isEmpty(student)) {
			sb.append(" and lower(r.student.name) like lower(:student)");
			params.put("student", student.concat("%"));
		}
		
		return registrations.search(sb.toString(), params);
	}


}
