package com.apple.shop.item;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public void saveItem(String title, Integer price, String username, String filename){

//        예외처리
        if (price < 0 || price > 1000000) {
            throw new RuntimeException("범위안에 들어가지 못하는 숫자임 ");
        }

        if(title.length() >5){
            throw new IllegalArgumentException("제목이 너무 길어요;");
        }

        if(username == null){
            throw new IllegalArgumentException("로그아웃상태임");
        }


//        강제로 에러 발생시키기
        Item item = new Item();
        item.setTitle(title);
        item.setPrice(price);
        item.setUsername(username);
        item.setImgurl(filename);
        itemRepository.save(item);

    }

    public void selectItem(Model model){
        List<Item> result = itemRepository.findAll();
        model.addAttribute("items",result);
    }

    public void itemPage(Model model, Integer no){
        Page<Item> result = itemRepository.findPageBy(PageRequest.of(no-1, 5));
        Integer pages = result.getTotalPages();
        model.addAttribute("items",result);
        model.addAttribute("pages",pages-1);
    }

    public void detailItem(Long id, Model model){
        Optional<Item> result = itemRepository.findById(id);

            if(!result.isPresent()){
                throw new IllegalArgumentException("없는값 넣지 마소서");
            }

            model.addAttribute("items", result.get());
    }

    public void updateItem(Long id, String title, Integer price){
        Optional<Item>optionalItem = itemRepository.findById(id);

        if(!optionalItem.isPresent()){
            throw new IllegalArgumentException("id 조회 x");
        }

        if (price < 0 || price > 1000000) {
            throw new RuntimeException("범위안에 들어가지 못하는 숫자임 ");
        }

        if(title.length() >5){
            throw new IllegalArgumentException("제목이 너무 길어요;");
        }

        Item item = optionalItem.get();
        item.setTitle(title);
        item.setPrice(price);
        itemRepository.save(item);

//        Item item = new Item();
//        item.setId(id);
//        item.setTitle(title);
//        item.setPrice(price);
//        itemRepository.save(item);
    }
}
