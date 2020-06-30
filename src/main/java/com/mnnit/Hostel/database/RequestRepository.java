package com.mnnit.Hostel.database;

import com.mnnit.Hostel.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RequestRepository extends JpaRepository<Request, String> {

    List<Request> findAllByStatus(int status);

}
