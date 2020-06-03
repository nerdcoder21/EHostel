package com.mnnit.Hostel.database;

import com.mnnit.Hostel.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository <User, String> {
    User findByUsername(String username);
}
