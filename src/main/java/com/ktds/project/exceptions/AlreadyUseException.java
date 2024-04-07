package com.ktds.project.exceptions;

public class AlreadyUseException extends RuntimeException {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2578523292465808716L;
	private String email;
	
	
	public String getEmail() {
		return email;
	}

	public AlreadyUseException(String email) {
		super("이미 사용중인 이메일입니다.");
		this.email = email;
	}
}
