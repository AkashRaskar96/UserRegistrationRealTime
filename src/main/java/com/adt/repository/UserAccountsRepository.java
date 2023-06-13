package com.adt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.adt.entity.UserAccountEntity;

public interface UserAccountsRepository extends JpaRepository<UserAccountEntity, Integer> {

	@Query(value = "select customer_id from cus where customer_id= :email;", nativeQuery = true)
	public boolean checkEmail(String email);
	
	public UserAccountEntity findByUserPazzwordAndUserEmail(String tempPwd, String email);
}
