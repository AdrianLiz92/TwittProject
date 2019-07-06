package com.twittproject.twittproject.service;

import com.twittproject.twittproject.entity.User;
import com.twittproject.twittproject.entity.UserDetails;
import com.twittproject.twittproject.model.UserDetailsDto;
import com.twittproject.twittproject.model.UserDto;
import com.twittproject.twittproject.repository.UserDetailsRepository;
import com.twittproject.twittproject.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private UserRepository userRepository;
    private UserDetailsRepository userDetailsRepository;
    private ModelMapper mapper;

    @Autowired
    public UserService(UserRepository userRepository, UserDetailsRepository userDetailsRepository,
                       ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.userDetailsRepository = userDetailsRepository;
        this.mapper = modelMapper;
    }

    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();

        return users.stream()
                .map(u -> mapper.map(u, UserDto.class))
                .collect(Collectors.toList());
    }

    public void saveNewUser(UserDto userDto, UserDetailsDto userDetailsDto) {

        if (userRepository.countUserByLogin(userDto.getLogin()) > 0) {
            throw new RuntimeException("User with provided login already exist");
        }

        if (userDetailsRepository.countByEmail(userDetailsDto.getEmail()) > 0) {
            throw new RuntimeException("User with provided email address already exists");
        }

        userDetailsDto.setJoinDate(new Date());
        UserDetails userDetails = mapper.map(userDetailsDto, UserDetails.class);
        userDetailsRepository.save(userDetails);

        userDto.setRole("ROLE_USER");
        userDto.setUserDetails(userDetails);
        User user = mapper.map(userDto, User.class);
        userRepository.save(user);
    }

    public boolean checkIfLockedOut(User loggedInUser) {
        Date unlockDate = loggedInUser.getUnlockDate();
        if (unlockDate == null) {
            return false;
        } else if (unlockDate.after(new Date())) {
            SecurityContextHolder.getContext().getAuthentication().setAuthenticated(false);
            SecurityContextHolder.clearContext();
            return true;
        } else {
            return false;
        }
    }
}
