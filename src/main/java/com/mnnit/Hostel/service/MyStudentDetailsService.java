package com.mnnit.Hostel.service;

import com.mnnit.Hostel.database.StudentRepository;
import com.mnnit.Hostel.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.stereotype.Service;

@Service
@Qualifier("MyStudentDetailsService")
public class MyStudentDetailsService  {

    @Autowired
    private StudentRepository studentRepository;

    public void addStudentDetails(Student student) throws Exception {
        try {
            studentRepository.save(student);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("not added");
        }
    }
}
