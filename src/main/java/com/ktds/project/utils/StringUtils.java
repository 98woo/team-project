package com.ktds.project.utils;

/**
 * 유틸리티들은 추상클래스로 만들어 인스턴스화를 못 하게 한다.
 */
public abstract class StringUtils {
	
	/**
	 * 생성자를 private 로 숨겨서 익명클래스도 못 만들게 만든다. 이렇게 하면 StirngUtils 클래스로 인스턴스를 절대 만들 수 없다.
	 */
	private StringUtils() {
		
	}
											// 전달 받은 데이터가 훼손되지 않도록 막아준다. utility 클래스에 final을 붙인다.
	// 메서드에 final을 붙여 오버라이딩을 막을 수도 있다. final
	public final static boolean correctPasswordFormat(final String str) {
		if (isEmpty(str)) {
			return false;
		}
		
		String passwordFormat = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$";
		return str.matches(passwordFormat);
	}
	
	public final static String replaceTagSymbols(final String str) {
		return nullToValue(str, "").replace(">", "&gt;")
					.replace("<", "&lt;")
					.replace(" ", "&nbsp;");
	}
	
	public final static String nullToValue(final String str, final String nullValue) {
		if (isEmpty(str)) {
			return nullValue;
		}
		
		return str;
	}
	
	public final static boolean isEmpty(final String str) {
		return str == null || str.trim().length() == 0;
	}
	
	public final static boolean isEnough(final String str, final int minLength) {
		if (isEmpty(str)) {
			return false;
		}
		
		return trim(str).length() >= minLength;
	}
	
	public final static String trim(final String str) {
		if (isEmpty(str)) {
			return null;
		}
		
		return str.trim();
	}
	
	public final static boolean isEmailFormat(final String str) {
		if (isEmpty(str)) {
			return false;
		}
		
		String emailPattern = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])";
		return str.matches(emailPattern);
	}
	
	public final static boolean isPhoneFormat(final String str) {
		if (isEmpty(str)) {
			return false;
		}
		
		String phoneFormat = "\\d{2,4}( |-|\\.)(\\d{4}( |-|\\.)\\d{4})";
		return str.matches(phoneFormat);
	}
	
}