package com.mnnit.Hostel.controller;

import com.mnnit.Hostel.database.HostelRepository;
import com.mnnit.Hostel.database.StudentRepository;
import com.mnnit.Hostel.database.UserRepository;
import com.mnnit.Hostel.model.Hostel;
import com.mnnit.Hostel.model.Student;
import com.mnnit.Hostel.model.User;
import com.mnnit.Hostel.service.MyUserDetailsService;
//import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.Collections;
import java.util.List;


@Controller
public class ApplicationController {

    @Autowired
    MyUserDetailsService userDetailsService;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    HostelRepository hostelRepository;

    @Autowired
    UserRepository userRepository;

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


    @RequestMapping("/student")
    public String studentInformation(Student student, Principal principal) {
        User user = userRepository.findByUsername(principal.getName());
        Student s = studentRepository.findStudentByUser(user);
        if (s == null)
            return "studentInformation";
        else
            return "updateStudentInformation";
    }

    @RequestMapping("/hostel-information")
    public ModelAndView hostelInformation(Principal principal) {
        ModelAndView mv = new ModelAndView();
        User user = userRepository.findByUsername(principal.getName());
        Student s = studentRepository.findStudentByUser(user);
        Hostel hostel = hostelRepository.findById(s.getHostelId());
        mv.setViewName("hostelInformation");
        mv.addObject("hostel_name", hostel.getName());
        mv.addObject("hostel_Id", hostel.getId());
        return mv;
    }

    @PostMapping("/student")
    public ModelAndView addStudentInformation(Student student, Principal principal) {
        ModelAndView mv = new ModelAndView();
        User user = userRepository.findByUsername(principal.getName());
        Student s = studentRepository.findStudentByUser(user);
        if (s == null) {
            student.setUser(user);
            studentRepository.save(student);
            mv.setViewName("home");
        } else {
            s.setAccountNumber(student.getAccountNumber());
            s.setRoom(student.getRoom());
            s.setAge(student.getAge());
            s.setCourse(student.getCourse());
            s.setIFSC(student.getIFSC());
            s.setHostelId(student.getHostelId());
            s.setDob(student.getDob());
            s.setName(student.getName());

            studentRepository.save(s);
            mv.setViewName("home");
        }
        return mv;
    }

    /*
        admin home
    */
    @RequestMapping({"home"})
    public String adminHome(){
        return "adminHome";
    }


    /*
       Dynamic page for hostel
   */
    @RequestMapping("/home/{id}/hostel")
    public ModelAndView showHostel(@PathVariable int id){

        ModelAndView mv = new ModelAndView("hostel");

        Hostel hostel = hostelRepository.findById(id);
        List<Integer> rooms = studentRepository.findRoomByHostelId(id);
        Collections.sort(rooms);

        mv.addObject("hostel", hostel);
        mv.addObject("title", hostel.getName());
        mv.addObject("occupied_rooms", rooms);

        return mv;
    }

    @RequestMapping("/your-room")
    public ModelAndView showRoomInformation(Principal principal) {
        ModelAndView mv = new ModelAndView("yourRoom");
        User user = userRepository.findByUsername(principal.getName());
        Student s = studentRepository.findStudentByUser(user);

        List<Student> list = studentRepository.findAllByHostelIdAndRoom(s.getHostelId(), s.getRoom());
        mv.addObject("roommates", list);
        mv.addObject("me", s);
        return mv;
    }
    @RequestMapping({"/","/user"})
    public String home(){
        return "home";
    }

}