package com.apple.shop;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController {


    private final ItemRepository itemRepository;

//    @Autowired 우클릭 Generate -> Constructor
//    public ItemController(ItemRepository itemRepository) {
//        this.itemRepository = itemRepository;
//    }
    // new ItemRepository() 하나 뽑아서 itemRepository 변수에 넣으라고 시키는 중

    @GetMapping("/list")
    String shopList(Model model){

        //JPA로 데이터 입출력하기
        // 1. repository 만들기
        // 2. 원하는 클래스에 repository 등록
        // 3. repository.입출력문법()쓰기


//        model.addAttribute("전달할데이터이름", "데이터");
        List<Item> result = itemRepository.findAll();
        //List 자료형
        System.out.println(result);

//        ArrayList<Integer> a = new ArrayList<>();
//        //다양한 타입 : object
//        a.add(30);
//        a.add(40);
//        System.out.println(a.get(0));


        model.addAttribute("items",result);
        return "list.html";
    }
}
