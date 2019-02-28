//package com.csy.springboot.dao;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//
//import com.csy.springboot.entity.User;
//import com.csy.springboot.mapper.UserMapper;
//
//@Repository
//public class UserDao {
//	
//	@Autowired
//	private UserMapper userMapper;
//	
//	public int insertUser(User user) throws Exception {
//		return this.userMapper.insertUser(user);
//	}
//
//	public User findUserByName(String name) {
//		return this.userMapper.findUserByName(name);
//	}
//}
