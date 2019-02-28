package com.csy.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csy.springboot.entity.User;
import com.csy.springboot.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public User findUserByName(String name) {
		return userRepository.findByAccount(name);
	}

	public String sayhelloworld(String user) {
		return user + " say helloworld !!!";
	}
}
