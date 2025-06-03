package com.apple.shop.item;

import com.apple.shop.Notice;
import com.apple.shop.NoticeRepository;
import com.apple.shop.comment.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    private final CommentService commentService;
    private final S3Service s3Service;

//    @Autowired 우클릭 Generate -> Constructor
//    public ItemController(ItemRepository itemRepository) {
//        this.itemRepository = itemRepository;
//    }
    // new ItemRepository() 하나 뽑아서 itemRepository 변수에 넣으라고 시키는 중

    @GetMapping("/list")
    String shopList(@RequestParam(required = false) String searchText,
                    @RequestParam(defaultValue = "1") Integer page,
                    Model model){


        Page<Item> result;
        if(searchText != null && !searchText.isBlank()){
            result = itemRepository.rawQuery1(searchText, PageRequest.of(page- 1, 5));
           // System.out.println(result.get());
        }else{
            result = itemRepository.findPageBy(PageRequest.of(page-1, 5));
           // System.out.println(result);
        }


        Integer pages = result.getTotalPages();

        System.out.println(pages);

        model.addAttribute("items",result);
        model.addAttribute("searchText",searchText);
        model.addAttribute("pages",pages-1);
        //JPA로 데이터 입출력하기
        // 1. repository 만들기
        // 2. 원하는 클래스에 repository 등록
        // 3. repository.입출력문법()쓰기


//        model.addAttribute("전달할데이터이름", "데이터");
       // itemService.selectItem(model);
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
    String addPost(String title, Integer price, String filename, Authentication auth) {
//        @RequestParam Map<String,Object> formData
//        Map formDate : Map 자료형으로 유저가 보낸 모든 데이터 변환해줌 -> 여러데이터 한 변수에 담고 싶을 때
//        HashMap<String, Object> test = new HashMap<>();
//        test.put("name", "kim");
//        test.put("age", 20);
        String username = "" ;
        if(auth != null){
            username = auth.getName();
        }
//        @ModelAttribute Item item

        itemService.saveItem(title, price, username, filename);
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
                commentService.detailComment(id, model);
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

    @PostMapping("/test2")
    String createId(){
        var result = new BCryptPasswordEncoder().encode("문자입니다.");

        return result;
    }

    @GetMapping("/list/page/{no}")
    String getListPage(Model model, @PathVariable Integer no){

        itemService.itemPage(model,no);
        return "list.html";
    }


    @GetMapping("/presigned-url")
    @ResponseBody
    String getURL(@RequestParam String filename){
        System.out.println(filename);
        var result = s3Service.createPresignedUrl("test/"+filename);
        System.out.println(result);
        return result;
    }

    @PostMapping("/search")
    String search(@RequestParam String searchText, Model model){
//        item테이블에서 searchText가 들어있는거 찾아서 가져와주세요
//        Page<Item> result = itemRepository.rawQuery1(searchText, PageRequest.of(no - 1, 5));
//        Integer pages = result.getTotalPages();
//        model.addAttribute("items",result);



        return "list.html";
    }



}
