package com.twittproject.twittproject.controller;

import com.twittproject.twittproject.model.PostDto;
import com.twittproject.twittproject.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
public class IndexController {

    @Autowired
    private PostService postService;

    @RequestMapping(value = {"/", "/index"})
    public String index() {
        return "index";
    }

    @GetMapping(value = {"/index", "/"})
    public String showAllPosts(Model model){
        List<PostDto> posts = postService.getAllPosts();
        model.addAttribute("allPosts", posts);

        Map<Long, String> idAndLogin = postService.getIdAndLogin();
        model.addAttribute("idAndLogin", idAndLogin);

        return "index";
    }
}
