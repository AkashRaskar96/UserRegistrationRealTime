package com.adt.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;
@Data
@Entity
@Table(name = "USER_ACCOUNTS")
public class UserAccountEntity {

	@Id
	@GeneratedValue
	@Column(name = "USER_ID")
	private Integer userId;
	@Column(name = "FIRST_NAME")
	private String firstName;
	@Column(name = "LAST_NAME")
	private String userLastName;
	@Column(name = "USER_EMAIL", unique = true)
	private String userEmail;
	@Column(name = "USER_PWD")
	private String userPazzword;
	@Column(name = "USER_MOBILE")
	private String userPhoneNumber;
	@Column(name = "DOB")
	private Date dateOfBirth;
	@Column(name = "GENDER")
	private Character gender;
	@Column(name = "CITY_ID")
	private Integer cityId;
	@Column(name = "STATE_ID")
	private Integer stateId;
	@Column(name = "COUNTRY_ID")
	private Integer countryId;
	@Column(name = "ACC_STATUS")
	private String accStatus;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED_DATE", updatable = false )
	private Date createdDate;
	
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATED_DATE", insertable = false)
	private Date updatedDate;
	
	@Column(name = "CREATED_BY")
	private String createdBy;
	@Column(name = "UPDATED_BY")
	private String updatedBy;
	
}
