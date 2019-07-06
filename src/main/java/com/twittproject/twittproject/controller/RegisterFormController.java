package com.twittproject.twittproject.controller;

import com.twittproject.twittproject.model.UserDetailsDto;
import com.twittproject.twittproject.model.UserDto;
import com.twittproject.twittproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class RegisterFormController {
    @Autowired
    private UserService userService;

    @GetMapping("/registerForm")
    public ModelAndView registerForm(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView
                .addObject("user", new  UserDto())
                .addObject("userDetails", new UserDetailsDto())
                .setViewName("registerForm");
        return modelAndView;
    }

    @PostMapping("/registerForm")
    public String saveNewUser(@ModelAttribute UserDto userDto,
                              @ModelAttribute UserDetailsDto userDetailsDto){
        userService.saveNewUser(userDto, userDetailsDto);
        return "redirect:login";
    }

}
