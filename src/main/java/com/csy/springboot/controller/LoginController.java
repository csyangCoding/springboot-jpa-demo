package com.csy.springboot.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.csy.springboot.entity.User;
import com.csy.springboot.service.UserService;
import com.csy.springboot.util.UserUtil;
import com.csy.springboot.vo.UserVO;

import io.swagger.annotations.Api;

@Api(value = "登录接口", tags = { "登录" })
@RequestMapping("/auth")
@RestController
public class LoginController {
	@Autowired
	private UserService userService;
	
	@PostMapping("/login")
	public void login(@RequestBody UserVO user, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		User saved = userService.findUserByName(user.getUserName());
		if (!user.getPassword().equals(saved.getPwd())) {
			response.sendError(404);
		}
		UserUtil.setUser(user.getUserName());
		String forword = request.getHeader("forword");
		forword.replace(request.getContextPath(), "");
		if(forword.contains("login") || forword.contains("register"))
		{
			response.sendRedirect("/index.jsp");
		}
		response.sendRedirect(forword);
	}

}
