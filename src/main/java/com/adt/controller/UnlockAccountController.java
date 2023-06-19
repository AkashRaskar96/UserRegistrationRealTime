package com.adt.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adt.domain.UnlockAccount;
import com.adt.domain.UserAccount;
import com.adt.service.UserManagementService;

@RestController
@RequestMapping("/unlockAcc")
public class UnlockAccountController {
	
	private static Logger logger=LoggerFactory.getLogger(UnlockAccountController.class);
	
	@Autowired
	private UserManagementService userMgmtService;

	@PostMapping("/userAcc")
	public String unlockUserAcc(@RequestBody UnlockAccount acc) {
		logger.info("*** Method Execution Stated **");
		UserAccount userAccnt = userMgmtService.getUserAccntByTempPwdAndEmail(acc.getTempPwd(),acc.getEmail());

		if (userAccnt != null) {
			// Update pwd & accnt_satus and display success msg
			userAccnt.setAccStatus("Un-Locked");
			userAccnt.setUserPazzword(acc.getNewPwd());
			boolean isUpdated = userMgmtService.updateUserAccount(userAccnt);
			if (isUpdated) {
				logger.info("");
				return "Unlock Account Successfully..!";
			}
		}
		logger.info("*** Method Execution Ended **");
		return "Please enter correct temporary password...?";
	}
}
