package com.linegames.assignment.userinfo.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "userinfo")
@Getter @Setter @ToString
public class UserInfo {
	
	@Id
	private String userId;
	
	@Column(name="password")
	private String password;
	
	@Column(name="authority")
	private String authority;
	
	@Column(name="email")
	private String email;
	
	@Column(name="createdatetime")
	private LocalDateTime createDateTime;
	
	@Column(name="modifydatetime")
	private LocalDateTime modifyDateTime;
}
