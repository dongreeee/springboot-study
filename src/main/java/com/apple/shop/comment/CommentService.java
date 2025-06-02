package com.apple.shop.comment;


import com.apple.shop.item.Item;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import software.amazon.awssdk.services.s3.endpoints.internal.Value;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    public void addContent(Long id, String username, String content ){

//
//        if(username == null){
//            throw new IllegalArgumentException("로그아웃상태임");
//        }
// 20250601 **** 추후 주문한 사람만 리뷰 작성할 수 있도록 코드 수정하기
        Comment comment = new Comment();
        comment.setParentId(id);
        comment.setUsername(username);
        comment.setContent(content);
        commentRepository.save(comment);


    }

    public void detailComment(Long id, Model model){
        List<Comment> result = commentRepository.findByParentId(id);


        model.addAttribute("comments", result);
    }



}
