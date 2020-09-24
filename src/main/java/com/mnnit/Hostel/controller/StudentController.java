package com.mnnit.Hostel.controller;

import com.mnnit.Hostel.model.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.Calendar;
import java.util.Date;
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


    /*
        For Handling notifications
    */
    @RequestMapping("/notification")
    public ModelAndView notification(Principal principal){

        ModelAndView mv = new ModelAndView();
        mv.setViewName("studentNotification");

        User user = userRepository.findByUsername(principal.getName());
        Student s = studentRepository.findStudentByUser(user);

        List<LeaveRequest> leaveRequests = leaveRequestRepository.findAllByRequestId_RegistrationNoAndStatus(s.getRegistrationNumber(), 0);
        List<MessRequest> messRequests = messRequestRepository.findAllByRequestId_RegistrationNoAndStatus(s.getRegistrationNumber(), 0);
        List<RoomRequest> roomRequests = roomRequestRepository.findAllByRequestId_RegistrationNoAndStatus(s.getRegistrationNumber(), 0);

        mv.addObject("leaveRequests", leaveRequests);
        mv.addObject("messRequests", messRequests);
        mv.addObject("roomRequests", roomRequests);
        mv.addObject("student", s);
        return mv;
    }


    @RequestMapping("/request")
    public String getRequest(LeaveRequest leaveRequest, MessRequest messRequest, RoomRequest roomRequest, Principal principal) {
        return "request";
    }

    @PostMapping("/leave/request")
    public String postLeaveRequest(LeaveRequest leaveRequest, Principal principal) {
        User user = userRepository.findByUsername(principal.getName());
        Student s = studentRepository.findStudentByUser(user);
        leaveRequest.setStatus(1);
        Date date = Calendar.getInstance().getTime();
        leaveRequest.setRequestId(new RequestId(s.getRegistrationNumber(),date));
        leaveRequestRepository.save(leaveRequest);
        return "redirect:/user/";
    }

    @PostMapping("/room/request")
    public String postRoomRequest(RoomRequest roomRequest, Principal principal) {
        User user = userRepository.findByUsername(principal.getName());
        Student s = studentRepository.findStudentByUser(user);
        Date date = Calendar.getInstance().getTime();
        roomRequest.setRequestId(new RequestId(s.getRegistrationNumber(),date));
        roomRequest.setStatus(1);
        roomRequestRepository.save(roomRequest);

        return "redirect:/user/";
    }

    @PostMapping("/mess/request")
    public String postMessRequest(MessRequest messRequest, Principal principal) {
        Date date = Calendar.getInstance().getTime();
        User user = userRepository.findByUsername(principal.getName());
        Student s = studentRepository.findStudentByUser(user);
        messRequest.setStatus(1);
        messRequest.setRequestId(new RequestId(s.getRegistrationNumber(), date));

        messRequestRepository.save(messRequest);

        return "redirect:/user/";
    }


}
