package com.adt.config;

import java.util.Properties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
@Configuration
public class ConfigurationClass {
	
	@Value("${spring.mail.host}")
	private String mailHost;
	
	@Value("${spring.mail.username}")
	private String mailuserName;
	
	@Value("${spring.mail.password}")
	private String mailPassword;
	
	@Value("${spring.mail.port}")
	private Integer mailPort;
	
	@Value("${spring.mail.protocol}")
	private String mailProtocol;
	
	@Value("${spring.mail.properties.mail.smtp.auth}")
	private String mailSmtpAuth;
	
//	@Value("${spring.mail.properties.mail.smtp.starttls.enable}")
	@Value("${spring.mail.smtp.starttls.enable}")
	private String mailSmtpStartTls;
	
	@Value("${spring.mail.default-encoding}")
	private String mailDefaultEncoding;
	

	
//			spring.mail.host=smtp.gmail.com
//			spring.mail.port=587
//			spring.mail.username=akashraskar.adt@gmail.com
//			#spring.mail.password=lpveonbrlffxqaka
//			spring.mail.password=onhvdxhrlmemftdr
//
//			spring.mail.default-encoding=UTF-8
//			spring.mail.protocol=smtp
//
//
//			#Other Properties
//			spring.mail.properties.mail.smtp.auth=true
//			spring.mail.properties.mail.smtp.connectiontimeout=5000
//			spring.mail.properties.mail.smtp.timeout=5000
//			spring.mail.properties.mail.smtp.writetimeout=5000
//
//			spring.mail.smtp.starttls.enable=true
//			spring.mail.debug=true
//			spring.mail.smtp.auth=true
//
//			#TLS, PORT 587
//			spring.mail.properties.mail.smtp.starttls.enable=true

	
	@Bean
	public JavaMailSender getjavaMailSender() {
		JavaMailSenderImpl mail=new JavaMailSenderImpl();
		
		mail.setHost(mailHost);
		mail.setPort(mailPort);
		mail.setUsername(mailuserName);
		mail.setPassword(mailPassword);
		mail.setDefaultEncoding(mailDefaultEncoding);
		
		Properties properties=new Properties();
	
		properties.put("mail.smtp.starttls.enable", mailSmtpStartTls);
		properties.put("spring.mail.properties.mail.smtp.auth", mailSmtpAuth);
		properties.put("spring.mail.transport.protocol", mailProtocol);
		mail.setJavaMailProperties(properties);

		
//		mail.setHost(env.getProperty("spring.mail.host"));
//		mail.setPort(Integer.valueOf(env.getProperty("spring.mail.port")));
//		mail.setUsername(env.getProperty("spring.mail.username"));
//		mail.setPassword(env.getProperty("spring.mail.password"));
//		
//		properties.put("spring.mail.starttls.enable", env.getProperty("spring.mail.starttls.enable"));
//		properties.put("spring.mail.smpt.auth", env.getProperty("spring.mail.smpt.auth"));
//		properties.put("spring.mail.transport.protocol", env.getProperty("spring.mail.transport.protocol"));
//		mail.setJavaMailProperties(properties);
		return mail;
	}
}
