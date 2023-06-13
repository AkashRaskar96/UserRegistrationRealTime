package com.adt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adt.entity.CountryMasterEntity;

public interface CountryMasterRepository extends JpaRepository<CountryMasterEntity, Integer> {

}
