package com.apple.shop.sales;

import com.apple.shop.item.Item;
import com.apple.shop.member.CustomUser;
import com.apple.shop.member.Member;
import com.apple.shop.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;


@Controller
@RequiredArgsConstructor
public class SalesController {

    private final SalesRepository salesRepository;
    private final MemberRepository memberRepository;

    @PostMapping("/sales")
    String addSales(String title, Integer price, Integer count, Authentication auth) {

        Long Userid = 0L;
        Optional<Member> result = Optional.empty();
        if(auth != null){
            result = memberRepository.findByUsername(auth.getName());
            //Userid = result.get().getId();
        }

        Member member = result.orElseThrow(() -> new IllegalArgumentException("사용자 정보를 찾을 수 없습니다."));


        Sales sales = new Sales();
        sales.setItemName(title);
        sales.setPrice(price);
        sales.setCount(count);
        CustomUser user = (CustomUser) auth.getPrincipal();
//        sales.setId(user.id);
        salesRepository.save(sales);
        return "redirect:/list";
    }

    @GetMapping("/salesList")
    String salesList(Model model){

        List<Sales> result = salesRepository.findAll();
      //System.out.println(result.get(0));
        model.addAttribute("orderList",result);
        return "salesList.html";
    }

}
