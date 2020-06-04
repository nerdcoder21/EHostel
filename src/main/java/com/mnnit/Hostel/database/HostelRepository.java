package com.mnnit.Hostel.database;

import com.mnnit.Hostel.model.Hostel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HostelRepository extends JpaRepository <Hostel, Integer> {
    Hostel findByHostelId(int hostelId);
}
