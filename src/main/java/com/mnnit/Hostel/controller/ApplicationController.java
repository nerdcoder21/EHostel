package com.mnnit.Hostel.controller;

import com.mnnit.Hostel.database.*;
import com.mnnit.Hostel.model.*;
import com.mnnit.Hostel.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

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

    @Autowired
    RequestRepository requestRepository;

    @Autowired
    MessRepository messRepository;


    @RequestMapping({"/","/user"})
    public RedirectView home(Principal principal){
        User user = userRepository.findByUsername(principal.getName());

        if(user.getRole().compareTo("USER") == 0){
            return new RedirectView("/user/home");
        }
        else{
            return new RedirectView("/admin/home");
        }
    }


    /*
        login page for users/admin
    */
    @RequestMapping("/login")
    public String loginPage(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            return "redirect:/user/";
        }
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
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            return "redirect:/user/";
        }

        return "signup";
    }


    @PostMapping("/signup")
    public String addUser(User user, ModelMap modelMap){

        try{
            userDetailsService.loadUserByUsername(user.getUsername());
        }catch (UsernameNotFoundException ue) {
            try {
                UserDetails userDetails = userDetailsService.registerUser(user);
                return "redirect:/user/student";
            } catch (Exception e) { e.printStackTrace(); }
        }

        modelMap.addAttribute("error_message", "Registration Unsuccessful!");
        return "signup";
    }



    @RequestMapping("/user/student")
    public String studentInformation(Student student, Principal principal) {
        User user = userRepository.findByUsername(principal.getName());

        Student s = studentRepository.findStudentByUser(user);
        if (s == null)
            return "studentInformation";
        else
            return "updateStudentInformation";
    }

/*
    @RequestMapping("/user/hostel-information")
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
*/



    @PostMapping("/user/student")
    public String addStudentInformation(Student student, Principal principal, ModelMap modelMap) {
        User user = userRepository.findByUsername(principal.getName());
        Student s = studentRepository.findStudentByUser(user);

        if (s == null) { //if form is not filled
            Student check = studentRepository.findStudentByRegistrationNumber(student.getRegistrationNumber());
            if (check != null) {
                modelMap.addAttribute("error_message", "Same Registration number already exists");
                return "redirect:/user/student";
            } else {
                student.setUser(user);
                studentRepository.save(student);
                return "redirect:/user/home";
            }
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
            return "redirect:/user/home";
        }
    }


    /*
        Home for student
    */
    @RequestMapping("/user/home")
    public ModelAndView showRoomInformation(Principal principal) {
        ModelAndView mv = new ModelAndView("userHome");

        User user = userRepository.findByUsername(principal.getName());

        Student student = studentRepository.findStudentByUser(user);
        if(student == null) {
            mv.setViewName("studentInformation");
            return mv;
        }

        Hostel hostel = hostelRepository.findById(student.getHostelId());

        List<Student> list = studentRepository.findAllByHostelIdAndRoom(student.getHostelId(), student.getRoom());
        list.remove(student);

        mv.addObject("roommates", list);
        mv.addObject("currentUser", student);
        mv.addObject("hostel", hostel);
        return mv;
    }














    /*
        admin home
    */
    @RequestMapping({"/admin/home"})
    public ModelAndView adminHome(){

        ModelAndView mv = new ModelAndView();
        mv.setViewName("adminHome");

        List<Hostel> hostels = hostelRepository.findAll();
        mv.addObject("hostels", hostels);

        return mv;
    }

    /*
       Dynamic page for hostel
   */
    @RequestMapping("/admin/home/{id}/hostel")
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

    /*
       Room details for admin
   */
    @RequestMapping({"/admin/home/{hostelId}/hostel/{roomId}/room"})
    public ModelAndView roomDetails(@PathVariable int hostelId, @PathVariable int roomId){

        ModelAndView mv = new ModelAndView("roomDetails");

        Hostel hostel = hostelRepository.findById(hostelId);
        List<Student> roomMates = studentRepository.findAllByHostelIdAndRoom(hostelId, roomId);
        mv.addObject("hostel", hostel);
        mv.addObject("title", "Room Details");
        mv.addObject("roomNo", roomId);
        mv.addObject("roommates", roomMates);

        return mv;
    }

    /*
        adding new student to room
        changing database.
   */
    @RequestMapping({"/admin/home/{hostelId}/hostel/{roomId}/room/{reg}"})
    public RedirectView addStudentToRoom(@PathVariable int hostelId, @PathVariable int roomId, @PathVariable("reg") String registrationNumber){

        try {
            Student student = studentRepository.findStudentByRegistrationNumber(registrationNumber);

            student.setHostelId(hostelId);
            student.setRoom(roomId);
            studentRepository.save(student);
        }catch (Exception e){
            System.out.println("Student Not found");
        }

        return new RedirectView("/admin/home/{hostelId}/hostel/{roomId}/room");
    }

    /*
        For Handling notifications
    */
    @RequestMapping("/admin/notification")
    public ModelAndView notification(){

        ModelAndView mv = new ModelAndView();
        mv.setViewName("notification");

        List<Request> requests = requestRepository.findAllByStatus(1);

        mv.addObject("requests", requests);

        return mv;
    }


    /*
    * Below functions are for handling different requests by admin
    * */
    @RequestMapping("/admin/notification/{reg}/mess")
    public RedirectView mess(@PathVariable("reg") String registrationNumber){

        Request request = requestRepository.getOne(registrationNumber);

        int days = (int)((request.getDateTo().getTime() - request.getDateFrom().getTime())/(1000*3600*24));
        Mess messAccount;
        try{
            messAccount = messRepository.findByRegistrationNumber(registrationNumber);
            messAccount.setHoliday(messAccount.getHoliday() + days);
        }catch (Exception e){
            messAccount = new Mess(registrationNumber, days, 0, 1);
        }
        messRepository.save(messAccount);

        request.setStatus(0);
        requestRepository.save(request);
        return new RedirectView("/admin/notification");
    }

    @RequestMapping("/admin/notification/{reg}/leave")
    public RedirectView hostelLeave(@PathVariable("reg") String registrationNumber){

        Request request = requestRepository.getOne(registrationNumber);
        Student student = studentRepository.findStudentByRegistrationNumber(registrationNumber);

        student.setHostelId(0);
        student.setRoom(0);
        studentRepository.save(student);

        request.setStatus(0);
        requestRepository.save(request);

        return new RedirectView("/admin/notification");
    }

    @RequestMapping("/admin/notification/{reg}/room/{roomNo}")
    public RedirectView mess(@PathVariable("reg") String registrationNumber, @PathVariable int roomNo){

        Request request = requestRepository.getOne(registrationNumber);

        if(roomNo != -1) {
            Student student = studentRepository.findStudentByRegistrationNumber(registrationNumber);

            student.setRoom(roomNo);
            studentRepository.save(student);
        }
        request.setStatus(0);
        requestRepository.save(request);

        return new RedirectView("/admin/notification");
    }



}