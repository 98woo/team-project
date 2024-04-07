package com.ktds.project.member.service;

import com.ktds.project.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.ktds.project.member.dao.MemberDao;
import com.ktds.project.member.vo.MemberVO;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberDao memberDao;

    @Override
    public MemberVO getMember(MemberVO memberVO) {

        // 1. 이메일로 저장되어 있는 salt 조회
        // 2. 먄약, salt 값이 null 이라면, 회원정보가 없는 것이므로 사용자에게 예외 전달.
        // 3. salt 값이 있을 경우, salt를 이용해 sha 암호화
        // 4. DB에서 암호화된 비밀번호와 이메일을 비교해 회원정보를 가져온다.

        // 암호화 전 로그인
        MemberVO member = this.memberDao.getMember(memberVO);



//        try {
//
//            if (employeeId == null || password == null) {
//                throw new IllegalArgumentException("사원번호나 비밀번호가 틀렸습니다");
//
//                if (loginTryCount == 5) {
//                    throw new IllegalArgumentException("로그인 횟수를 초과했습니다.\n 60분 뒤에 다시 시도하세요.");
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        return member;
    }

}
