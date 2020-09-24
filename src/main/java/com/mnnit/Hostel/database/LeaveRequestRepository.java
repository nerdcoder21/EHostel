package com.mnnit.Hostel.database;

import com.mnnit.Hostel.model.LeaveRequest;
import com.mnnit.Hostel.model.RequestId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, RequestId> {
    List<LeaveRequest> findAllByStatus(int status);
    List<LeaveRequest> findAllByRequestId_RegistrationNoAndStatus(String registrationNo, int status);

}
