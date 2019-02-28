package com.csy.springboot.exception;

public class UnloginException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private static final String ERROR_CODE = "NOTLOGIN";
	private String msg;

	public static String getErrorCode() {
		return ERROR_CODE;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public UnloginException() {
	}

	public UnloginException(String message) {
		super(message);
		this.msg = message;
	}

	public UnloginException(Throwable cause) {
		super(cause);
	}

	public UnloginException(String message, Throwable cause) {
		super(message, cause);
	}

	public UnloginException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
