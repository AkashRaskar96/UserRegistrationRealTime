package com.adt.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "CITIES_MASTER")
public class CityMasterEntity {
	@Id
//	@GeneratedValue
	@Column(name = "CITY_ID")
	private Integer id;
	
	@Column(name = "CITY_NAME")
	private String cityName;
	@Column(name = "STATE_ID")
	private Integer stateId;
}
