package com.src.hdfs.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class User implements Serializable {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;
	private String username;
	private String password;
	private String role;
	private String provider;//google or facebook
	private String providerid;
	
	@CreationTimestamp
	public Timestamp createdate;
	@CreationTimestamp
	public Timestamp lastdate;
	
	@Builder
	public User(int id, String username, String password, String role, String provider, String providerid,
			Timestamp createdate, Timestamp lastdate) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.role = role;
		this.provider = provider;
		this.providerid = providerid;
		this.createdate = createdate;
		this.lastdate = lastdate;
	}
	




	

	
}
