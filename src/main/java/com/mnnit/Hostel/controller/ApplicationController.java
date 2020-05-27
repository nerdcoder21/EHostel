package com.mnnit.Hostel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ApplicationController {

    @RequestMapping("/login")
    public String loginPage(){
        return "login";
    }

    @RequestMapping("/user")
    public String home(){
        return "home";
    }

    @RequestMapping("/logout")
    public String logout(){
        return "login";
    }

}
