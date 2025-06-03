package com.apple.shop.member;

import com.apple.shop.item.Item;
import com.apple.shop.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public void saveMember(String username, String password, String displayname) throws Exception{

        Optional<Member> result = memberRepository.findByUsername(username);
        if (result.isPresent()){
            throw new Exception("존재하는아이디");
        }
        if (username.length() < 8 || password.length() < 8){
            throw new Exception("너무짧음");
        }


        var pw_encode = passwordEncoder.encode(password);


        Member member = new Member();
        member.setUsername(username);
        member.setPassword(pw_encode);
        member.setDisplayname(displayname);

        memberRepository.save(member);



    }
}
