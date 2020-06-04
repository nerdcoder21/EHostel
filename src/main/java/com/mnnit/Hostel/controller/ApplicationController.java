package com.mnnit.Hostel.controller;

import com.mnnit.Hostel.database.HostelRepository;
import com.mnnit.Hostel.model.Hostel;
import com.mnnit.Hostel.model.User;
import com.mnnit.Hostel.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ApplicationController {

    @Autowired
    MyUserDetailsService userDetailsService;

    @Autowired
    HostelRepository hostelRepository;

    /*
        login page for users/admin
    */
    @RequestMapping("/login")
    public String loginPage(){
        return "login";
    }

    /*
        after logging out
    */
    @RequestMapping("/logout-success")
    public String logout(){
        return "logout";
    }


    /*
        registration page
    */
    @RequestMapping("/signup")
    public String signUp(User user){
        return "signup";
    }

    /*
        For registering a user.
    */
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

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /*
        home page for student
    */
    @RequestMapping({"/","/user"})
    public String home(){
        return "home";
    }

    /*
        admin home
    */
    @RequestMapping({"home"})
    public String adminHome(){
        return "adminHome";
    }

    /*
       Filling up student information
    */
    @RequestMapping("/student")
    public String studentInformation() {
        return "studentInformation";
    }


    /*
        Dynamic page for hostel
    */
    @RequestMapping("/home/{id}/hostel")
    public ModelAndView showHostel(@PathVariable int id){

        ModelAndView mv = new ModelAndView("hostel");

        Hostel hostel = hostelRepository.findByHostelId(id);

        mv.addObject("hostel", hostel);
        mv.addObject("title", hostel.getHostelName());

        return mv;
    }


}