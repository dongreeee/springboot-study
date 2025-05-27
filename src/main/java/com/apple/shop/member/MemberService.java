package com.apple.shop.member;

import com.apple.shop.item.Item;
import com.apple.shop.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public void saveMember(String username, String password, String displayname){

        var pw_encode = new BCryptPasswordEncoder().encode(password);


        Member member = new Member();
        member.setUsername(username);
        member.setPassword(pw_encode);
        member.setDisplayname(displayname);
        memberRepository.save(member);

    }
}
