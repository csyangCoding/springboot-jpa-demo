package com.csy.springboot.exception;

public class NoPermissionException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6436849609738002023L;

	private static final String ERROR_CODE = "NoPermission";
	private String msg;
	private String currentUser;

	public NoPermissionException() {
		super();
	}
	
	public NoPermissionException(String msg) {
		super();
		this.msg = msg;
	}
	
	public NoPermissionException(String currentUser,String msg) {
		super();
		this.msg = msg;
		this.currentUser = currentUser;
	}

	public static String getErrorCode() {
		return ERROR_CODE;
	}

	public String getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(String currentUser) {
		this.currentUser = currentUser;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
