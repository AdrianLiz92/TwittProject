package com.twittproject.twittproject.controller;

import com.twittproject.twittproject.model.CommentDto;
import com.twittproject.twittproject.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;


@Controller
public class AddCommentController {
    @Autowired
    private CommentService commentService;
    private String commentedPostId;

    @GetMapping("/addComment")
    public ModelAndView addComment(@RequestParam String postId) {
        commentedPostId = postId;
        return new ModelAndView("addComment", "newCommentToAdd",
                new CommentDto());
    }

    @PostMapping("/addComment")
    public String addComment(@ModelAttribute CommentDto commentDto) throws ParseException {
        commentService.saveComment(commentDto, commentedPostId);
        return "redirect:index";
    }
}
