package com.ktds.project.member.service;

import com.ktds.project.member.vo.MemberVO;

public interface MemberService {

	/**
	 * 로그인시 필요한 사원 정보를 가져온다.
	 * @param memberVO 
	 * @return
	 */
	MemberVO getMember(MemberVO memberVO);

}
