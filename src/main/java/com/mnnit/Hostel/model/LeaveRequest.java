package com.mnnit.Hostel.model;

import org.springframework.lang.Nullable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class LeaveRequest {

    @EmbeddedId
    private RequestId requestId;
    @Nullable
    private int status;

    public LeaveRequest() {
    }

    public LeaveRequest(RequestId requestId, int status) {
        this.requestId = requestId;
        this.status = status;
    }

    public RequestId getRequestId() {
        return requestId;
    }

    public void setRequestId(RequestId requestId) {
        this.requestId = requestId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
