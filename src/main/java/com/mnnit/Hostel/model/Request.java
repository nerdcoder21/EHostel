package com.mnnit.Hostel.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Request {

    @Id
    @Column(name = "registrationNo")
    private String registrationNumber;
    private String type;
    private int status;

    /*request made for mess account.*/
    private Date dateFrom;
    private Date dateTo;

    /*request made for room change*/
    private int toRoom;

    public Request() {
    }

    public Request(String registrationNumber, String type, int status, Date dateFrom, Date dateTo) {
        this.registrationNumber = registrationNumber;
        this.type = type;
        this.status = status;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    public Request(String registrationNumber, String type, int status, Date dateFrom) {
        this.registrationNumber = registrationNumber;
        this.type = type;
        this.status = status;
        this.dateFrom = dateFrom;
    }

    public Request(String registrationNumber, String type, int status, int toRoom) {
        this.registrationNumber = registrationNumber;
        this.type = type;
        this.status = status;
        this.toRoom = toRoom;
    }


    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public String getType() {
        return type;
    }

    public int getStatus() {
        return status;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public int getToRoom() {
        return toRoom;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public void setToRoom(int toRoom) {
        this.toRoom = toRoom;
    }

    @Override
    public String toString() {
        return "Request{" +
                "registrationNumber='" + registrationNumber + '\'' +
                ", type='" + type + '\'' +
                ", status='" + status + '\'' +
                ", dateFrom=" + dateFrom +
                ", dateTo=" + dateTo +
                ", toRoom=" + toRoom +
                '}';
    }
}
