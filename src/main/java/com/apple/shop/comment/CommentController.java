package com.apple.shop.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequiredArgsConstructor
public class CommentController {

    private final CommentRepository commentRepository;
    private final CommentService commentService;

    @PostMapping("/comments")
    String addContent(Long id, String username, String content) {


        commentService.addContent(id, username, content);
        return "redirect:/detail/" + id;
    }


}
