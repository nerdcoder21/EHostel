package com.mnnit.Hostel.model;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;

import java.util.Date;

@Entity
public class Student {

    /*Private Fields*/

    @Id
    @Column(name = "registrationNo")
    private String registrationNumber;
    private String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dob;

    private int age;
    private String course;
    private int semester;
    private int hostelId;
    private int room;
    @Column(name = "accNo")
    private String accountNumber;
    @Column(name = "ifsc")
    private String IFSC;

    @OneToOne
    private User user;

    public Student() {
    }

    public Student(String registrationNumber, String name, Date dob, int age, String course, int semester, int hostelId, int room, String accountNumber, String IFSC) {
        this.registrationNumber = registrationNumber;
        this.name = name;
        this.dob = dob;
        this.age = age;
        this.course = course;
        this.semester = semester;
        this.hostelId = hostelId;
        this.room = room;
        this.accountNumber = accountNumber;
        this.IFSC = IFSC;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public String getName() {
        return name;
    }

    public Date getDob() {
        return dob;
    }

    public int getAge() {

        //age =
        return age;
    }

    public String getCourse() {
        return course;
    }

    public int getSemester() {
        return semester;
    }

    public int getHostelId() {
        return hostelId;
    }

    public int getRoom() {
        return room;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getIFSC() {
        return IFSC;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public void setHostelId(int hostelId) {
        this.hostelId = hostelId;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setIFSC(String IFSC) {
        this.IFSC = IFSC;
    }

    @Override
    public String toString() {
        return "Student{" +
                "registrationNumber='" + registrationNumber + '\'' +
                ", name='" + name + '\'' +
                ", dob=" + dob +
                ", age=" + age +
                ", course='" + course + '\'' +
                ", semester=" + semester +
                ", hostelId=" + hostelId +
                ", room=" + room +
                ", accountNumber='" + accountNumber + '\'' +
                ", IFSC='" + IFSC + '\'' +
                '}';
    }

    public void setAge(int age) {
        this.age = age;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

