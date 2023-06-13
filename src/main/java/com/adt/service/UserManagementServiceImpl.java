package com.adt.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adt.constants.AppConstants;
import com.adt.domain.UserAccount;
import com.adt.entity.CityMasterEntity;
import com.adt.entity.CountryMasterEntity;
import com.adt.entity.StatesMasterEntity;
import com.adt.entity.UserAccountEntity;
import com.adt.repository.CityMasterRepository;
import com.adt.repository.CountryMasterRepository;
import com.adt.repository.StatesMasterRepository;
import com.adt.repository.UserAccountsRepository;
import com.adt.util.EmailUtils;
import com.adt.util.PwdUtils;

@Service
public class UserManagementServiceImpl implements UserManagementService {

	
	@Autowired
	private CountryMasterRepository cmRepo;

	@Autowired
	private StatesMasterRepository smRepo;

	@Autowired
	private CityMasterRepository citymRepo;

	@Autowired
	private UserAccountsRepository userAccRepo;

	@Autowired
	private EmailUtils emailUtils;

	@Override
	public Map<Integer, String> getAllCountries() {
		Map<Integer, String> map = new LinkedHashMap<>();
		List<CountryMasterEntity> countryList = cmRepo.findAll();
		countryList.stream().forEach(countryEntity -> {
			map.put(countryEntity.getId(), countryEntity.getCountryName());
		});
		return map;
	}

	@Override
	public Map<Integer, String> getStatesByCountryId(Integer countryId) {
		Map<Integer, String> map = new LinkedHashMap<>();
		List<StatesMasterEntity> stateList = smRepo.findByCountryId(countryId);
		stateList.stream().forEach(stateEntity -> {
			map.put(stateEntity.getStateId(), stateEntity.getStateName());
		});
		return map;
	}

	@Override
	public Map<Integer, String> getCitiesByStateId(Integer stateId) {
		Map<Integer, String> map = new LinkedHashMap<>();
		List<CityMasterEntity> cityList = citymRepo.findByStateId(stateId);
		cityList.stream().forEach(cityEntity -> {
			map.put(cityEntity.getId(), cityEntity.getCityName());
		});
		return map;
	}

	@Override
	public boolean saveUserAcc(UserAccount userAcc) {
		userAcc.setUserPazzword(PwdUtils.generateTempPwd(AppConstants.TEMP_PWD_LENGTH));
		userAcc.setAccStatus(AppConstants.LOCKED_STR);

		UserAccountEntity entity = new UserAccountEntity();
		BeanUtils.copyProperties(userAcc, entity);

		UserAccountEntity savedEntity = userAccRepo.save(entity);

		if (savedEntity.getUserId() != null) {
			return emailUtils.sendUserAccUnlockEmail01(userAcc);
		}
		return false;
	}

	@Override
	public boolean isTempPwdValid(String tempPwd, String email) {
		UserAccountEntity entity = userAccRepo.findByUserPazzwordAndUserEmail(tempPwd, email);
		return entity != null;
	}

	@Override
	public UserAccount getUserAccntByTempPwdAndEmail(String tempPwd, String email) {
		UserAccountEntity entity = userAccRepo.findByUserPazzwordAndUserEmail(tempPwd, email);
		UserAccount accnt = null;
		if (entity != null) {
			accnt = new UserAccount();
			BeanUtils.copyProperties(entity, accnt);
		}
		return accnt;
	}

	@Override
	public boolean updateUserAccount(UserAccount userAcc) {
		UserAccountEntity entity = new UserAccountEntity();
		BeanUtils.copyProperties(userAcc, entity);
		UserAccountEntity savedEntity = userAccRepo.save(entity);
		return savedEntity != null;
	}
}
