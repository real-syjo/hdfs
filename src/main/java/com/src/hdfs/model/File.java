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
public class File {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;
	
	private String filenm;
	private String orignm;
	private int filesize;
	private String filetype;
	private String username;
	private String boardid;
	private String hdfsdir;
	@CreationTimestamp
	public Timestamp createdate;
}
