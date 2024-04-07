package com.ktds.project.member.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ktds.project.member.service.MemberService;
import com.ktds.project.member.vo.MemberVO;

import jakarta.servlet.http.HttpSession;


@Controller
public class MemberController {
	
	private MemberService memberService; 
	
	public MemberController() {
		
	}


	@GetMapping("/member/login")
	public String viewLoginPage() {
		return "member/memberlogin";
	}
	
	@PostMapping("/member/login")
	public String doLogin(MemberVO memberVO, HttpSession session) {
		MemberVO member = this.memberService.getMember(memberVO);
		session.setAttribute("_LOGIN_USER_", member);
		session.setMaxInactiveInterval(1200);
		
		return "member/memberlogin";
	}
	

}
