package com.twittproject.twittproject.controller;

import com.twittproject.twittproject.entity.Post;
import com.twittproject.twittproject.model.CommentDto;
import com.twittproject.twittproject.service.CommentService;
import com.twittproject.twittproject.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
public class ShowCommentsController {

    private PostService postService;
    private CommentService commentService;

    @Autowired
    public ShowCommentsController(PostService postService, CommentService commentService) {
        this.postService = postService;
        this.commentService = commentService;
    }

    @GetMapping("/showComments")
    public String showComments (@RequestParam String postId, Model model){
        Long parentPostId = Long.valueOf(postId);

        Post parentPost = postService.getPostById(parentPostId);
        model.addAttribute("parentPost", parentPost);

        List<CommentDto> comments = commentService.getCommentsForPost(Long.valueOf(postId));
        model.addAttribute("allComments", comments);

        return "/showComments";
    }
}
