package com.mnnit.Hostel.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Mess {

    @Id
    @Column(name = "registrationNo")
    private String registrationNumber;

    private int holiday;
    private int refund;
    private int status;

    public Mess() {
    }

    public Mess(String registrationNumber, int holiday, int refund, int status) {
        this.registrationNumber = registrationNumber;
        this.holiday = holiday;
        this.refund = refund;
        this.status = status;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public void setHoliday(int holiday) {
        this.holiday = holiday;
    }

    public void setRefund(int refund) {
        this.refund = refund;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public int getHoliday() {
        return holiday;
    }

    public int getRefund() {
        return refund;
    }

    public int getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Mess{" +
                "registrationNumber='" + registrationNumber + '\'' +
                ", holiday=" + holiday +
                ", refund=" + refund +
                ", status='" + status + '\'' +
                '}';
    }
}
