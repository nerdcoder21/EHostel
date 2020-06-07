package com.mnnit.Hostel.database;

import com.mnnit.Hostel.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student, String> {

    @Query("select room from Student where hostelId = ?1")
    List<Integer> findRoomByHostelId(int hostelId);
}