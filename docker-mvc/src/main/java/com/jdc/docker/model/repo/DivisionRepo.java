package com.jdc.docker.model.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jdc.docker.model.entity.Division;

public interface DivisionRepo extends JpaRepository<Division, Integer>{

}
