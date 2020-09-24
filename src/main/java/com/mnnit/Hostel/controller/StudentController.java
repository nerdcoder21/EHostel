package com.mnnit.Hostel.controller;

import com.mnnit.Hostel.model.Hostel;
import com.mnnit.Hostel.model.Request;
import com.mnnit.Hostel.model.Student;
import com.mnnit.Hostel.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;


@Controller
@RequestMapping({"/user/"})
public class StudentController extends ApplicationController{


    @RequestMapping("/student")
    public String studentInformation(Student student, Principal principal) {
        User user = userRepository.findByUsername(principal.getName());

        Student s = studentRepository.findStudentByUser(user);
        if (s == null)
            return "studentInformation";
        else
            return "updateStudentInformation";
    }


    @PostMapping("/student")
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
    @RequestMapping("/home")
    public ModelAndView showRoomInformation(Student student, Principal principal) {
        ModelAndView mv = new ModelAndView("userHome");

        User user = userRepository.findByUsername(principal.getName());

        Student s = studentRepository.findStudentByUser(user);
        if(s == null) {
            mv.setViewName("studentInformation");
            return mv;
        }

        Hostel hostel = hostelRepository.findById(s.getHostelId());

        List<Student> list = studentRepository.findAllByHostelIdAndRoom(s.getHostelId(), s.getRoom());
        list.remove(s);

        mv.addObject("roommates", list);
        mv.addObject("currentUser", s);
        mv.addObject("hostel", hostel);
        return mv;
    }




    @RequestMapping("/request")
    public String getRequest(Request request, Principal principal) {
        return "request";
    }

    @PostMapping("/request")
    public String postRequest(Request request, Principal principal) {
        User user = userRepository.findByUsername(principal.getName());
        Student s = studentRepository.findStudentByUser(user);
        request.setRegistrationNumber(s.getRegistrationNumber());
        request.setStatus(1);
        requestRepository.save(request);

        return "redirect:/user/";
    }


}
