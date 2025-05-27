package com.apple.shop.member;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;
    private final MemberService memberService;

    @GetMapping("/join")
    String joinMember(){
        return "join.html";
    }

    @PostMapping("/joinMember")
    String addPost(String username, String password, String displayname) {
        System.out.println(username);
        System.out.println(password);
        System.out.println(displayname);
        memberService.saveMember(username, password, displayname);
        return "redirect:/list";
    }
}
