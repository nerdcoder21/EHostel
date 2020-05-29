package com.mnnit.Hostel.controller;

import com.mnnit.Hostel.model.User;
import com.mnnit.Hostel.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ApplicationController {

    @Autowired
    MyUserDetailsService userDetailsService;

    @RequestMapping("/login")
    public String loginPage(){
        return "login";
    }

    @RequestMapping({"/","/user"})
    public String home(){
        return "home";
    }

    @RequestMapping("/logout-success")
    public String logout(){
        return "logout";
    }

    @RequestMapping("/signup")
    public String signUp(User user){
        return "signup";
    }

    @PostMapping("/signup")
    public String addUser(User user){

        UserDetails userDetails = userDetailsService.registerUser(user);

        return "home";
    }

}