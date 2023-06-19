package com.adt.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.adt.domain.UnlockAccount;
import com.adt.domain.UserAccount;
import com.adt.service.UserManagementService;

@RestController
@RequestMapping("/user")
public class UserManagementController {

	private static  Logger logger=LoggerFactory.getLogger(UserManagementController.class);

	@Autowired
	private UserManagementService userMgmtService;

	@GetMapping("/countries")
	public Map<Integer, String> getCountries() {
		return userMgmtService.getAllCountries();
	}

	@GetMapping("/states")
	public Map<Integer, String> getStatesByCountryId(@RequestParam("cid") Integer countryId) {
		return userMgmtService.getStatesByCountryId(countryId);
	}

	@GetMapping("/cities")
	public Map<Integer, String> getCitiesByStateId(@RequestParam("sid") Integer stateId) {
		Map<Integer, String> map = null;
		try {
			map=userMgmtService.getCitiesByStateId(stateId);
		}catch(Exception e) {
			
		}
		return map;
	}

	@PostMapping("/saveuser")
	public String saveUser(@RequestBody UserAccount userAcc) {
		logger.info("*** Methed Execution Started ***");
		boolean result = false;
		try {
			result = userMgmtService.saveUserAcc(userAcc);
		} catch (Exception e) {
			logger.error("Error Occured while Saving User :: " + e.getMessage());
		}
		logger.info("*** Method Execution Ended ***");
		return "User Save Status :: " + result;
	}
	
	@GetMapping("/isExistEmail/{email}")
	public ResponseEntity<Boolean> isExistEmail(@PathVariable String email){
		logger.info("*** Methed Execution Started ***");
		boolean result=false;
		try {
			result=userMgmtService.isExistEmail(email);
		}catch (Exception e) {
			logger.error("Exception Occured while Mail Checking "+e.getMessage());
		}
		logger.info("*** Method Execution Ended ***");
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
	

}
