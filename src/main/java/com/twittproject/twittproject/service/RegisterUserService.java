package com.twittproject.twittproject.service;

import com.twittproject.twittproject.entity.User;
import com.twittproject.twittproject.model.UserDto;
import com.twittproject.twittproject.repository.RegisterUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

@Service
public class RegisterUserService {
    private RegisterUserRepository repository;
    private ModelMapper mapper;

    @Autowired
    public RegisterUserService(RegisterUserRepository repository, ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public void addNewUser (UserDto userDto) {
        User userToSave = mapper.map(userDto, User.class);
        repository.save((userToSave));

    }
}
