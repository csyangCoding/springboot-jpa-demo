package com.csy.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.csy.springboot.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	public User findByAccount(String account);
}
