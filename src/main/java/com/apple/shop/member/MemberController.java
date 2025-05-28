package com.apple.shop.member;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;
    private final MemberService memberService;

    @GetMapping("/join")
    public String joinMember(){
        return "join.html";
    }

    @PostMapping("/joinMember")
    public String addPost(String username, String password, String displayname, Model model) throws Exception {

        try{
            memberService.saveMember(username, password, displayname);
            return "redirect:/list";
        }
        catch (Exception e){
            String msg = switch (e.getMessage()){
                case "존재하는아이디" -> "이미 존재하는 아이디입니다.";
                case "너무짧음" -> "아이디와 비밀번호는 8자 이상이어야 합니다.";
                default -> "회원가입 중 오류가 발생했습니다.";
            };
            model.addAttribute("errorMsg", msg);
            return "join";

        }

    }

    @GetMapping("/login")
    public String login(){
        return "login.html";
    }

    @GetMapping("/mypage")
    public String mypage(Authentication auth){
        System.out.println(auth);
        System.out.println(auth.getName());
        System.out.println(auth.isAuthenticated());
//1. 셋팅만 하면 세션방식 로그인기능 구현 끝
//2. 클래스에 가이드주려면 interface/implements
//3. Controller에서 유저 로그인정보 출력가능
//4. Thymeleaf html 파일에서도 가능
//5. @PreQuthorize쓰면 API들 로그인검사 편함
        return "mypage.html";
    }


}
