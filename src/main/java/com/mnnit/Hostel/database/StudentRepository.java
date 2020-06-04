package com.mnnit.Hostel.database;

import com.mnnit.Hostel.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, String> {
    //Student findByRegistrationNumber(String registrationNumber);
}