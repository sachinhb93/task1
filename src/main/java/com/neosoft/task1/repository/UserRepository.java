package com.neosoft.task1.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.neosoft.task1.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	public User findByfirstName(@Param("firstName") String firstName);

	public User findBysurName(@Param("surName") String surName);

	public User findBypincode(@Param("pincode") String pincode);

	public List<User> findByOrderByDOBAsc();

	public List<User> findByOrderByJoiningDateAsc();

}
