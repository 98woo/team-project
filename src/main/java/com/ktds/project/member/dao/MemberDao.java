package com.ktds.project.member.dao;

import com.ktds.project.member.vo.MemberVO;

public interface MemberDao {
	
	public String MDAO_SPACE = "com.ktds.project.member.dao.MemberDao";

	/**
	 * 로그인 시도하는 사원의 정보
	 * @param memberVO 로그인 시도하는 사원의 사원 번호
	 * @return
	 */
	MemberVO getMember(MemberVO memberVO);

	/**
	 * 
	 * @param memberVO 로그인 시도하는 사원의 비밀번호
	 * @return
	 */
	MemberVO getPassword(MemberVO memberVO);

	/**
	 * 
	 * @param memberVO 로그인 시도하는 횟수
	 * @return
	 */
	int getLoginTryCount(MemberVO memberVO);

}
