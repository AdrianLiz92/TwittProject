package com.twittproject.twittproject.service;

import com.twittproject.twittproject.AuxiliaryMethods;
import com.twittproject.twittproject.entity.Comment;
import com.twittproject.twittproject.entity.Post;
import com.twittproject.twittproject.entity.User;
import com.twittproject.twittproject.model.PostDto;
import com.twittproject.twittproject.model.UserDto;
import com.twittproject.twittproject.repository.CommentRepository;
import com.twittproject.twittproject.repository.PostRepository;
import com.twittproject.twittproject.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {
    private PostRepository postRepository;
    private ModelMapper modelMapper;
    private UserRepository userRepository;
    private AuxiliaryMethods auxMethods;
    private CommentRepository commentRepository;

    @Autowired
    public PostService(PostRepository postRepository, ModelMapper modelMapper,
                       UserRepository userRepository, AuxiliaryMethods auxMethods,
                       CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
        this.userRepository= userRepository;
        this.auxMethods = auxMethods;
        this.commentRepository = commentRepository;
    }

    public void savePost (PostDto postDto) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-ddd HH:mm:ss");
        String newDateFormat = sdf.format(new Date());
        postDto.setCreateDate(sdf.parse(newDateFormat));

        String login="";
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            login = ((UserDetails)principal).getUsername();
        }else{
            login = principal.toString();
        }
        User loggedUser = userRepository.findUserByLogin(login).get();
        postDto.setUser(modelMapper.map(loggedUser, UserDto.class));

        Post newPostToAdd = modelMapper.map(postDto, Post.class);
        postRepository.save(newPostToAdd);
    }

    public List<PostDto> getAllPosts(){
        /*List<Post> posts = postRepository.findAll();*/
        List<Post> posts = postRepository.findAllByOrderByCreateDateDesc();
        return posts.stream()
                .map(u-> modelMapper.map(u, PostDto.class))
                .collect(Collectors.toList());
    }

    /**
     * Sets date and time of deletion for Post and Comments related to it.
     *
     * @param postDto
     * @throws ParseException
     */
    public void markPostAsDeleted (PostDto postDto) throws ParseException {
        Long id =  postDto.getId();
        Post postToBeDeleted =  postRepository.getOne(id);
        Date currentDateAndTime = auxMethods.getCurrentDateTime();

        postToBeDeleted.setDeleteDate(currentDateAndTime);
        postRepository.save(postToBeDeleted);

        List<Comment> commentsToBeDeleted = commentRepository.findAllCommentsByPost_idEquals(id);
        for (Comment comment : commentsToBeDeleted) {
            comment.setDeleteDate(currentDateAndTime);
            commentRepository.save(comment);
        }

    }




}
