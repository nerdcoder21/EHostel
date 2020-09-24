package com.mnnit.Hostel.model;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Date;

@Embeddable
public class RequestId implements Serializable {
    private String registrationNo;
    private Date requestDate;

    public RequestId() {
    }

    public RequestId(String registrationNo, Date requestDate) {
        this.registrationNo = registrationNo;
        this.requestDate = requestDate;
    }

    public String getRegistrationNo() {
        return registrationNo;
    }

    public void setRegistrationNo(String registrationNo) {
        this.registrationNo = registrationNo;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }
}
