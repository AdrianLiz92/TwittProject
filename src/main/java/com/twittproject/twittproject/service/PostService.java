package com.twittproject.twittproject.service;

import com.twittproject.twittproject.entity.Post;
import com.twittproject.twittproject.model.PostDto;
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

@Service
public class PostService {
    private PostRepository postRepository;
    private ModelMapper modelMapper;
    private UserRepository userRepository;

    @Autowired
    public PostService(PostRepository postRepository, ModelMapper modelMapper, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
        this.userRepository= userRepository;
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

        Long loggedUserId = userRepository.findUserByLogin(login).get().getId();

        postDto.setUserId(loggedUserId);

        Post newPostToAdd = modelMapper.map(postDto, Post.class);
        postRepository.save(newPostToAdd);
    }
}
