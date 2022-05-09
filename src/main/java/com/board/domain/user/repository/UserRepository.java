package com.board.domain.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.board.domain.user.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String>{
	
	User findByUserId(String userId);

	Optional<User> findByUserIdAndPassword(String userId, String password);

}
