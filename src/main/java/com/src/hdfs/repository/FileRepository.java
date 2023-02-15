package com.src.hdfs.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.src.hdfs.model.File;

public interface FileRepository extends JpaRepository<File, Integer> {
	
	public List<File> findByBoardid(String boardid);
	
	public List<File> findByUsername(String username);
	
	public List<File> findByUsernameAndFilenm(String username, String filenm);
	
	public File findByUsernameAndOrignm(String username, String orignm);
	
	@Transactional
	public void deleteByBoardid(String boardid);
}
