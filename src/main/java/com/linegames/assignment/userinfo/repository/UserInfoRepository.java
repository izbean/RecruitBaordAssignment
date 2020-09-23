package com.linegames.assignment.userinfo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.linegames.assignment.userinfo.entity.UserInfo;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, String>{
	
	public UserInfo findByUserId(String userId);
	public boolean findByUserIdAndPassword(String userId, String password);
	
}
