package com.adt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adt.entity.StatesMasterEntity;

public interface StatesMasterRepository extends JpaRepository<StatesMasterEntity, Integer> {
	
	public List<StatesMasterEntity> findByCountryId(Integer countryId);
}
