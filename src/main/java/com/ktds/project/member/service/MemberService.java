package com.ktds.project.member.service;

import com.ktds.project.member.vo.MemberVO;

public interface MemberService {

	/**
	 * 로그인할 떄 사원번호와 비밀번호를 조회
	 * @param memberVO 
	 * @return
	 */
	MemberVO getMember(MemberVO memberVO);

}
