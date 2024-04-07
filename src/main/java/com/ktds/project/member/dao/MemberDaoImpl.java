package com.ktds.project.member.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

import com.ktds.project.member.vo.MemberVO;

public class MemberDaoImpl extends SqlSessionDaoSupport implements MemberDao{

	@Autowired
	@Override
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		super.setSqlSessionTemplate(sqlSessionTemplate);
	}
	
	@Override
	public MemberVO getMember(MemberVO memberVO) {
		return getSqlSession().selectOne(MemberDao.MDAO_SPACE + ".getMember", memberVO);
	}

	@Override
	public MemberVO getPassword(MemberVO memberVO) {
		return null;
	}

	@Override
	public int getLoginTryCount(MemberVO memberVO) {
		return 0;
	}

}
