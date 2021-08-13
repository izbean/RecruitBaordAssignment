package com.recruit.assignment.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.recruit.assignment.user.User;

@Repository
public interface UserRepository extends JpaRepository<User, String>{
	
	User findByUserId(String userId);
	boolean findByUserIdAndPassword(String userId, String password);

}
