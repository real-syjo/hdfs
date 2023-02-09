package com.src.hdfs.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import lombok.Data;

@Entity
@Data
public class Board {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;
	
	private String boardid;
	private String boardnm;
	private long filesum;
	private int filecnt;
	private String username;
	
	@CreationTimestamp
	public Timestamp createdate;
}
