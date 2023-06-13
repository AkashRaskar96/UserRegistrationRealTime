package com.adt.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
		return userMgmtService.getCitiesByStateId(stateId);
	}

	@PostMapping("/saveuser")
	public String saveUser(@RequestBody UserAccount userAcc) {
		boolean result = userMgmtService.saveUserAcc(userAcc);
		return "User Save Status :: " + result;
	}

}
