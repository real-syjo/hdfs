package com.src.hdfs.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.src.hdfs.model.File;

public interface FileRepository extends JpaRepository<File, Integer> {
	
	public List<File> findByBoardid(String boardid);
	
	public List<File> findByUsername(String username);
	
	public File deleteByBoardid(String boardid);
}
