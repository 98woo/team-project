package com.ktds.project.member.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.ktds.project.member.dao.MemberDao;
import com.ktds.project.member.vo.MemberVO;

public class MemberServiceImpl implements MemberService{

	@Autowired
	private MemberDao memberDao;
	
	@Override
	public MemberVO getMember(MemberVO memberVO) {
		MemberVO employeeId = this.memberDao.getMember(memberVO);
		MemberVO password = this.memberDao.getPassword(memberVO);
		int loginTryCount = this.memberDao.getLoginTryCount(memberVO);
		try {
			
		
		
		if(employeeId == null || password == null) {
			throw new IllegalArgumentException("사원번호나 비밀번호가 틀렸습니다");
			loginTryCount += 1;
			if (loginTryCount == 5) {
				throw new IllegalArgumentException("로그인 횟수를 초과했습니다.\n 60분 뒤에 다시 시도하세요.");
			}
		}
		} catch (Exception e){
			e.printStackTrace();
		}
		
		return null;
	}

}
