package com.adt.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.adt.domain.UserAccount;

@Component
public class EmailUtils {

	@Autowired
	private JavaMailSender mailSender;
	
	public boolean sendUserAccUnlockEmail01(UserAccount userAcc) {
		boolean isSent=false;
		try {
			SimpleMailMessage msg=new SimpleMailMessage();
			msg.setTo(userAcc.getUserEmail());
			msg.setSubject("Registration Email");
			msg.setText(getUnlockAccEmailBody(userAcc));
//			mailSender.send(msg);
			isSent=true;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return isSent;
	}
	
	public boolean sendUserAccUnlockEmail(UserAccount usserAcc) {
		boolean isSent=false;
		try {
			MimeMessage mimeMsg=mailSender.createMimeMessage();
			MimeMessageHelper helper=new MimeMessageHelper(mimeMsg);
			helper.setTo(usserAcc.getUserEmail());
			helper.setSubject("Unlock your Account");
			helper.setText(getUnlockAccEmailBody(usserAcc),true);
//			mailSender.send(mimeMsg);
			isSent = true;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return isSent;
	}
	
	private String getUnlockAccEmailBody(UserAccount acc) throws IOException {
		StringBuffer sb=new StringBuffer("");
		
		FileReader fr=new FileReader("UNLOCK-ACC-EMAIL-BODY-TEMPLATE.txt");
		BufferedReader br=new BufferedReader(fr);
		String line=br.readLine();
		
		while(line != null) {
			sb.append(line);
			line=br.readLine();
		}
		
		br.close();
		String mailBody = sb.toString();
		mailBody = mailBody.replace("{FNAME}", acc.getFirstName());
		mailBody = mailBody.replace("{LNAME}", acc.getUserLastName());
		mailBody = mailBody.replace("{TEMP-PWD}", acc.getUserPazzword());
		mailBody = mailBody.replace("{EMAIL}", acc.getUserEmail());
		
		return mailBody;
	}
}
