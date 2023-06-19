package com.adt.service;

import java.util.Map;

import com.adt.domain.UserAccount;

public interface UserManagementService {

	public Map<Integer, String> getAllCountries();

	public Map<Integer, String> getStatesByCountryId(Integer countryId);

	public Map<Integer, String> getCitiesByStateId(Integer stateId);

	public boolean saveUserAcc(UserAccount userAcc);

	public boolean isTempPwdValid(String tempPwd, String email);

	public UserAccount getUserAccntByTempPwdAndEmail(String tempPwd, String email);

	public boolean updateUserAccount(UserAccount userAcc);
	
	public boolean isExistEmail(String email);

}
