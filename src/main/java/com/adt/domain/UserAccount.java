package com.adt.domain;

import java.util.Date;
import lombok.Data;

@Data
public class UserAccount {
	private Integer userId;
	private String firstName;
	private String userLastName;
	private String userEmail;
	private String userPazzword;
	private String userPhoneNumber;
	private Date dateOfBirth;
	private Character gender;
	private Integer cityId;
	private Integer stateId;
	private Integer countryId;
	private String accStatus;
	private Date createdDate;
	private Date updatedDate;
	private String createdBy;
	private String updatedBy;
}
