package com.twittproject.twittproject.controller;

import com.twittproject.twittproject.AuxiliaryMethods;
import com.twittproject.twittproject.entity.User;
import com.twittproject.twittproject.model.PostDto;
import com.twittproject.twittproject.service.PostService;
import com.twittproject.twittproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.ParseException;
import java.util.List;

@Controller
public class IndexController {

    private PostService postService;
    private UserService userService;
    private AuxiliaryMethods auxMethod;

    @Autowired
    public IndexController(PostService postService, UserService userService,
                           AuxiliaryMethods auxMethod) {
        this.postService = postService;
        this.userService = userService;
        this.auxMethod = auxMethod;
    }

    @RequestMapping(value = {"/", "/index"})
    public String index() {
        return "index";
    }

    @GetMapping(value = {"/index", "/"})
    public String showAllPosts(Model model){
        User loggedInUser = auxMethod.getLoggedInUser();
        if (userService.checkIfLockedOut(loggedInUser)){
            return "login";
        }

        List<PostDto> posts = postService.getAllPosts();
        model.addAttribute("allPosts", posts);
        model.addAttribute("loggedInUser", loggedInUser);

        return "index";
    }

    @PostMapping("/deletePost")
    public String deletePost(@ModelAttribute("post") PostDto postDto) throws ParseException {
        postService.markPostAsDeleted(postDto);
        return "redirect:index";
    }
}
