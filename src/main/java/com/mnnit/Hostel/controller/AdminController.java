package com.mnnit.Hostel.controller;

import com.mnnit.Hostel.model.Hostel;
import com.mnnit.Hostel.model.Mess;
import com.mnnit.Hostel.model.Request;
import com.mnnit.Hostel.model.Student;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping({"/admin/"})
public class AdminController extends ApplicationController {


    /*
    admin home
    */
    @RequestMapping({"/home"})
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

    /*
       Room details for admin
   */
    @RequestMapping({"/home/{hostelId}/hostel/{roomId}/room"})
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
    @RequestMapping({"/home/{hostelId}/hostel/{roomId}/room/{reg}"})
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
    @RequestMapping("/notification")
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
    @RequestMapping("/notification/{reg}/mess")
    public RedirectView messRequest(@PathVariable("reg") String registrationNumber){

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

    @RequestMapping("/notification/{reg}/leave")
    public RedirectView hostelLeaveRequest(@PathVariable("reg") String registrationNumber){

        Request request = requestRepository.getOne(registrationNumber);
        Student student = studentRepository.findStudentByRegistrationNumber(registrationNumber);

        student.setHostelId(0);
        student.setRoom(0);
        studentRepository.save(student);

        request.setStatus(0);
        requestRepository.save(request);

        return new RedirectView("/admin/notification");
    }

    @RequestMapping("/notification/{reg}/room/{roomNo}")
    public RedirectView roomChangeRequest(@PathVariable("reg") String registrationNumber, @PathVariable int roomNo){

        Request request = requestRepository.getOne(registrationNumber);
        Student student = studentRepository.findStudentByRegistrationNumber(registrationNumber);

        List<Student> list = studentRepository.findAllByHostelIdAndRoom(student.getHostelId(), roomNo);
        Hostel hostel = hostelRepository.findById(student.getHostelId());

        if(list.size() >= hostel.getRoomCapacity())
            roomNo = -1;

        if(roomNo != -1) {
            student.setRoom(roomNo);
            studentRepository.save(student);
        }
        request.setStatus(0);
        requestRepository.save(request);

        return new RedirectView("/admin/notification");
    }
}
