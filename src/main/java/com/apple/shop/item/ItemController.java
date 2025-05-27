package com.apple.shop.item;

import com.apple.shop.Notice;
import com.apple.shop.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController {


    private final ItemRepository itemRepository;
    private final NoticeRepository noticeRepository;
    private final ItemService itemService;

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
        itemService.selectItem(model);
        return "list.html";
    }

    @GetMapping("/notice")
    String noticeList(Model model) {
        List<Notice> result = noticeRepository.findAll();
        model.addAttribute("notices", result);
        return "notice.html";
    }

    @GetMapping("/write")
    String write() {
        return "write.html";
    }

    @PostMapping("/add")
    String addPost(String title, Integer price) {
//        @RequestParam Map<String,Object> formData
//        Map formDate : Map 자료형으로 유저가 보낸 모든 데이터 변환해줌 -> 여러데이터 한 변수에 담고 싶을 때
//        HashMap<String, Object> test = new HashMap<>();
//        test.put("name", "kim");
//        test.put("age", 20);

//        @ModelAttribute Item item

        itemService.saveItem(title, price);
        return "redirect:/list";
    }
//    유저가 입력한 값 전송 -> Post 요청

//    상품추가 기능
//    1. 상품 이름, 가격 작성할 수 있는 페이지와 폼
//    2. 전송버튼 누르면 서버로 보냄 -> 검사 후 이상 없으면 DB에 저장

//    상세페이지
    @GetMapping("/detail/{id}")
    String detailItem(@PathVariable Long id, Model model){

                itemService.detailItem(id, model);
                return "detail.html";


    }

    @GetMapping("/updateView/{id}")
    String UpdateView(@PathVariable Long id, Model model){

        itemService.detailItem(id, model);
        return "update.html";


    }

    @PostMapping("/update/{id}")
    String UpdateItem(@PathVariable Long id, @RequestParam String title, @RequestParam Integer price){
        itemService.updateItem(id,title,price);
        return "redirect:/list";
    }

    @DeleteMapping("/delete")
    ResponseEntity<String> DeleteItem(@RequestBody Item body){
        itemRepository.deleteById(body.getId());
        return ResponseEntity.status(200).body("삭제완료");
    }

//    상품 삭제기능 만들기
//    1. 상품마다 삭제버튼 만들고
//    2. 누르면 서버에 요청하고 서버는 DB에 있던 행 삭제


}
