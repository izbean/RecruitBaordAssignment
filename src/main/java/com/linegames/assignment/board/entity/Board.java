package com.linegames.assignment.board.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="board")
@Getter @Setter @ToString
public class Board {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int idx;
	
	@Column
	String title;
	
	@Column
	String content;
	
	@Column
	String createId;
	
	@Column
	LocalDateTime createDateTime;
	
	@Column
	String modifyId;
	
	@Column
	LocalDateTime modifyDateTime;
}
