package com.recruit.assignment.domain.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.recruit.assignment.domain.board.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board, Integer>{

}
