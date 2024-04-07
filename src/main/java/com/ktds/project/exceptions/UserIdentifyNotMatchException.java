package com.ktds.project.exceptions;

public class UserIdentifyNotMatchException extends RuntimeException {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4956833376636002474L;

	public UserIdentifyNotMatchException() {
		super("이메일 또는 비밀번호가 일치하지 않습니다.");
	}
}
