package com.twittproject.twittproject.controller;

import com.twittproject.twittproject.model.PostDto;
import com.twittproject.twittproject.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;

@Controller
public class AddPostController {
    @Autowired
    private PostService postService;

    @GetMapping("/addPost")
    public ModelAndView addPost() {
        return new ModelAndView("addPost","newPostToAdd",
                new PostDto());
    }

    @PostMapping("/addPost")
    public String addPost(@ModelAttribute PostDto postDto) throws ParseException {
        postService.savePost(postDto);
        return "redirect:index";
    }
}
