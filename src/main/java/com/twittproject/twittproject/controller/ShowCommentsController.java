package com.twittproject.twittproject.controller;

import com.twittproject.twittproject.AuxiliaryMethods;
import com.twittproject.twittproject.entity.Post;
import com.twittproject.twittproject.entity.User;
import com.twittproject.twittproject.model.CommentDto;
import com.twittproject.twittproject.repository.PostRepository;
import com.twittproject.twittproject.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.ParseException;
import java.util.List;


@Controller
public class ShowCommentsController {

    private PostRepository postRepository;
    private CommentService commentService;
    private AuxiliaryMethods auxMethods;

    @Autowired
    public ShowCommentsController(PostRepository postRepository, CommentService commentService, AuxiliaryMethods auxMethods) {
        this.postRepository = postRepository;
        this.commentService = commentService;
        this.auxMethods = auxMethods;
    }

    @GetMapping("/showComments")
    public String showComments (@RequestParam String postId, Model model){
        Long parentPostId = Long.valueOf(postId);

        User loggedInUser = auxMethods.getLoggedInUser();
        model.addAttribute("loggedInUser", loggedInUser);

        Post parentPost = postRepository.getOne(parentPostId);
        model.addAttribute("parentPost", parentPost);

        List<CommentDto> comments = commentService.getCommentsForPost(Long.valueOf(postId));
        model.addAttribute("allComments", comments);

        return "/showComments";
    }
    @PostMapping("/deleteComment")
    public String deleteComment(@ModelAttribute("comment") CommentDto commentDto, RedirectAttributes redirectAttributes) throws ParseException {
        redirectAttributes.addAttribute("postId",String.valueOf(commentDto.getParentPostId()));
        commentService.markCommentAsDeleted(commentDto);
        return "redirect:showComments";
    }
}
