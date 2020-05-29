package com.mnnit.Hostel.controller;

import com.mnnit.Hostel.model.User;
import com.mnnit.Hostel.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
    public ModelAndView addUser(User user){

        ModelAndView mv = new ModelAndView();


        try{
            userDetailsService.loadUserByUsername(user.getUsername());
        }catch (UsernameNotFoundException ue) {
            try {
                UserDetails userDetails = userDetailsService.registerUser(user);
                mv.setViewName("home");
                return mv;
            } catch (Exception e) { e.printStackTrace(); }
        }

        mv.setViewName("signup");
        mv.addObject("error_message", "Registration Unsuccessful!");
        return mv;
    }

}