package com.ktds.project.member.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberController {
	
	public MemberController() {
		
	}


	@GetMapping("/member/login")
	public String viewLoginPage() {
		return "member/memberlogin";
	}

}
