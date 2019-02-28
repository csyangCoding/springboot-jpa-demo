package com.csy.springboot.vo;

import java.io.Serializable;

public class UserVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4427554878417198794L;

	private String userName;
	private String password;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
