package com.ktds.project.member.web;

import com.ktds.project.utils.AjaxResponse;
import com.ktds.project.utils.Validator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.ktds.project.member.service.MemberService;
import com.ktds.project.member.vo.MemberVO;

import jakarta.servlet.http.HttpSession;

import java.util.List;
import java.util.Map;


@Controller
public class MemberController {

    private MemberService memberService;

    public MemberController() {

    }


    @GetMapping("/member/login")
    public String viewLoginPage() {
        return "member/memberlogin";
    }

    // 로그인 메서드
    @ResponseBody
    @PostMapping("/ajax/member/login")
    public AjaxResponse doLogin(MemberVO memberVO, HttpSession session, @RequestParam(defaultValue = "/management/main") String nextUrl) {

        Validator<MemberVO> validator = new Validator<>(memberVO);

        validator.add("empId", Validator.Type.NOT_EMPTY, "사원번호를 입력해주세요.")
                .add("empId", Validator.Type.EMPID, "사원번호 형식으로 입력해주세요.")
                .add("password", Validator.Type.NOT_EMPTY, "비밀번호를 입력해주세요.")
                .add("password", Validator.Type.PASSWORD, "비밀번호 형식으로 입력해주세요.")
                .start();

        if (validator.hasErrors()) {
            Map<String, List<String>> errors = validator.getErrors();
            return new AjaxResponse().append("errors", errors);
        }

        MemberVO member = this.memberService.getMember(memberVO);
        session.setAttribute("_LOGIN_USER_", member);
        session.setMaxInactiveInterval(1200);

        return new AjaxResponse().append("next", nextUrl);
    }

    // 로그아웃 메서드
    @GetMapping("/member/logout")
    public String doLogout(HttpSession session) {
        session.invalidate();
        return "redirect:/management/memberlogin";
    }
}
