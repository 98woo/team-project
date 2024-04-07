package com.ktds.project.member.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

import com.ktds.project.member.vo.MemberVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
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
	public MemberVO selectEmpIdAndPwd(MemberVO memberVO) {
		return getSqlSession().selectOne(MemberDao.MDAO_SPACE + ".selectEmpAndPwd", memberVO);
	}


}
