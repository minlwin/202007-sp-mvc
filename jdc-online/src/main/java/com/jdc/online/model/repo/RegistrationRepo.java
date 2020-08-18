package com.jdc.online.model.repo;

import java.util.List;

import com.jdc.online.model.BaseRepository;
import com.jdc.online.model.entity.Registration;

public interface RegistrationRepo extends BaseRepository<Registration, Integer>{

	List<Registration> findByClassRoomId(int id);

}
