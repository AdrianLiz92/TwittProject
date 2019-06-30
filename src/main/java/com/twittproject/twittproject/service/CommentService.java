package com.twittproject.twittproject.service;

import com.twittproject.twittproject.AuxiliaryMethods;
import com.twittproject.twittproject.entity.Comment;
import com.twittproject.twittproject.entity.Post;
import com.twittproject.twittproject.entity.User;
import com.twittproject.twittproject.model.CommentDto;
import com.twittproject.twittproject.model.PostDto;
import com.twittproject.twittproject.model.UserDto;
import com.twittproject.twittproject.repository.CommentRepository;
import com.twittproject.twittproject.repository.PostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {
    private CommentRepository commentRepository;
    private ModelMapper modelMapper;
    private AuxiliaryMethods auxMet;
    private PostRepository postRepository;


    @Autowired
    public CommentService(CommentRepository commentRepository, ModelMapper modelMapper,
                          AuxiliaryMethods auxMet, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.modelMapper = modelMapper;
        this.auxMet = auxMet;
        this.postRepository = postRepository;

    }

    public void saveComment(CommentDto commentDto, String id) throws ParseException {

        commentDto.setCreateDate(auxMet.getCurrentDateTime());

        User loggedUser = auxMet.getLoggedInUser();
        commentDto.setUser(modelMapper.map(loggedUser, UserDto.class));

        Post parentPost = postRepository.findPostById(Long.valueOf(id)).get();

        commentDto.setPost(modelMapper.map(parentPost, PostDto.class));

        Comment newCommentToAdd = modelMapper.map(commentDto, Comment.class);
        commentRepository.save(newCommentToAdd);

    }

    public List<CommentDto> getCommentsForPost(Long postId) {
        List<Comment> comments = commentRepository.findAllCommentsByPost_idEquals(postId);

        return comments.stream()
                .map(u -> modelMapper.map(u, CommentDto.class))
                .collect(Collectors.toList());
    }
}
