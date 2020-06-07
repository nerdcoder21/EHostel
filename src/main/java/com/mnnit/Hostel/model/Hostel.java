package com.mnnit.Hostel.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Hostel {
    @Id
    private int id;
    private String name;
    private int rooms;
    private int capacity;

    public Hostel() {
    }

    public Hostel(int id, String name, int rooms, int capacity) {
        this.id = id;
        this.name = name;
        this.rooms = rooms;
        this.capacity = capacity;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRooms(int rooms) {
        this.rooms = rooms;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getRooms() {
        return rooms;
    }

    public int getCapacity() {
        return capacity;
    }

    @Override
    public String toString() {
        return "Hostel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", rooms=" + rooms +
                ", capacity=" + capacity +
                '}';
    }
}

