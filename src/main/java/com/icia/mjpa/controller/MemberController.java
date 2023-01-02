package com.icia.mjpa.controller;

import com.icia.mjpa.dto.MemberDTO;
import com.icia.mjpa.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService msvc;

    // 회원가입 페이지 요청
    @GetMapping("/join")
    public String join() {
        return "join";
    }

    // 회원가입 메소드
    @PostMapping("/join")
    public String join(@ModelAttribute MemberDTO member) {

        msvc.join(member);

        return "index";
    }

    // 회원 리스트
    @GetMapping("/list")
    public String list(Model model) {

        List<MemberDTO> memberList = msvc.list();

        model.addAttribute("memberList", memberList);
        return "list";
    }

    // 회원정보 상세보기 메소드
    @GetMapping("/view/{idx}")
    public String view(@PathVariable long idx, Model model) {

        MemberDTO member = msvc.view(idx);

        if (member != null) {
            // idx로 찾는 데이터가 존재하면
            // member 데이터를 view라는 이름으로
            // view.html에서 사용
            model.addAttribute("view", member);
            return "view";
        } else {
            return "index";
        }

    }

    // 회원 수정 페이지 이동
    @GetMapping("/modify/{idx}")
    public String modify(@PathVariable long idx, Model model) {
        MemberDTO member = msvc.view(idx);
        model.addAttribute("modify", member);
        return "modify";
    }

    // 회원 수정 메소드
    @PostMapping("/modify")
    public String modify(@ModelAttribute MemberDTO member) {
        msvc.modify(member);
        return "redirect:/view/" + member.getIdx();
    }

    // 회원 삭제 메소드
    @GetMapping("/delete/{idx}")
    public String delete(@PathVariable Long idx, Model model) {
        msvc.delete(idx);
        return "redirect:/list";
    }

    // 로그인 페이지 요청
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    // 로그인 메소드
    @PostMapping("/login")
    public String login(@ModelAttribute MemberDTO member, HttpSession session) {
        MemberDTO loginMember = msvc.login(member);

        if (loginMember != null) {
            session.setAttribute("login", loginMember);
            session.setAttribute("loginId", loginMember.getMemId());
        }
        return "index";
    }

    // 로그아웃 메소드
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "index";
    }

}
