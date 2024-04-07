package com.ktds.project.exceptions;

public class FileNotExistsException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3659346964692465733L;

	public FileNotExistsException() {
		super("파일을 찾을 수 없습니다.");
	}
	

}
