package com.mnnit.Hostel.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Hostel {
    @Id
    private int id;
    private String name;
    private int rooms;
    private int roomCapacity;

    public Hostel() {
    }

    public Hostel(int id, String name, int rooms, int capacity) {
        this.id = id;
        this.name = name;
        this.rooms = rooms;
        this.roomCapacity = capacity;
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

    public void setRoomCapacity(int roomCapacity) {
        this.roomCapacity = roomCapacity;
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

    public int getRoomCapacity() {
        return roomCapacity;
    }

    @Override
    public String toString() {
        return "Hostel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", rooms=" + rooms +
                ", roomCapacity=" + roomCapacity +
                '}';
    }
}

