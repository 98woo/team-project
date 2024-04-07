package com.ktds.project.member.dao;

import com.ktds.project.member.vo.MemberVO;

public interface MemberDao {
	
	public String MDAO_SPACE = "com.ktds.project.member.dao.MemberDao";

	/**
	 * 로그인에 필요한 사원 정보들을 memberVO 객체로 전달 받아 가져온다.
	 * @param memberVO 로그인 시도하는 사원의 사원 번호
	 * @return
	 */
	MemberVO getMember(MemberVO memberVO);




}
