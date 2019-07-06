package com.twittproject.twittproject.controller;

import com.twittproject.twittproject.model.UserDto;
import com.twittproject.twittproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class UserManagementController {
    private UserService userService;

    @Autowired
    public UserManagementController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/userManagement")
    public String showAllUsers (Model model) {
        List<UserDto> userList =  userService.getAllUsers();
        model.addAttribute("userList", userList);

        return "/userManagement";
    }
}
