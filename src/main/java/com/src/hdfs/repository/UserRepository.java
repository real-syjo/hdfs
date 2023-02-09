package com.src.hdfs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.src.hdfs.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	public User findByUsername(String username);
	
	public User findByProviderid(String providerId);
}
