package com.mnnit.Hostel.database;

import com.mnnit.Hostel.model.Mess;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessRepository extends JpaRepository<Mess, String> {
    Mess findByRegistrationNumber(String registrationNumber);
}
