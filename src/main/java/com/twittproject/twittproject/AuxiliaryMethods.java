package com.twittproject.twittproject;

import com.twittproject.twittproject.entity.User;
import com.twittproject.twittproject.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class AuxiliaryMethods {
    private UserRepository userRepository;

    public AuxiliaryMethods(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Date getCurrentDateTime() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-ddd HH:mm:ss");
        String newDateFormat = sdf.format(new Date());
        return sdf.parse(newDateFormat);
    }

    public User getLoggedInUser(){
        String login="";
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            login = ((UserDetails)principal).getUsername();
        }else{
            login = principal.toString();
        }
        return userRepository.findUserByLogin(login).get();

    }
}
