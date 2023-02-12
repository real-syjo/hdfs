package com.src.hdfs.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.src.hdfs.model.Board;

public interface BoardRepository extends JpaRepository<Board, Integer>{

	public List<Board> findByUsername(String username);
	
	@Transactional
	public Board deleteByBoardid(String boardid);
	
}