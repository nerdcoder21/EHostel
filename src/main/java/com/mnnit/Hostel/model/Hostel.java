package com.mnnit.Hostel.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Hostel {
    @Id
    private int hostelId;
    private String hostelName;
    private int hostelRooms;

    public Hostel() {
    }

    public Hostel(int hostelId, String hostelName, int hostelRooms) {
        this.hostelId = hostelId;
        this.hostelName = hostelName;
        this.hostelRooms = hostelRooms;
    }

    public void setHostelId(int hostelId) {
        this.hostelId = hostelId;
    }

    public void setHostelName(String hostelName) {
        this.hostelName = hostelName;
    }

    public void setHostelRooms(int hostelRooms) {
        this.hostelRooms = hostelRooms;
    }

    public int getHostelId() {
        return hostelId;
    }

    public String getHostelName() {
        return hostelName;
    }

    public int getHostelRooms() {
        return hostelRooms;
    }

    @Override
    public String toString() {
        return "Hostel{" +
                "hostelId=" + hostelId +
                ", hostelName='" + hostelName + '\'' +
                ", hostelRooms=" + hostelRooms +
                '}';
    }
}

