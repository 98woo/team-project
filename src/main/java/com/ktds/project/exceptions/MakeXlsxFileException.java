package com.ktds.project.exceptions;
public class MakeXlsxFileException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3474762443132266098L;

	public MakeXlsxFileException() {
		super("엑셀 파일을 찾을 수 없습니다.");
	}
}
