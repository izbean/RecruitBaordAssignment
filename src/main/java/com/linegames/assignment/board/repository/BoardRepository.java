package com.linegames.assignment.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.linegames.assignment.board.entity.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board, Integer>{
	public Board findByIdx(int idx);
}
