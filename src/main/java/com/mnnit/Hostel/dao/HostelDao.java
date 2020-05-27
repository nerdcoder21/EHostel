package com.mnnit.Hostel.dao;

import com.mnnit.Hostel.model.Hostel;

import java.util.List;

public interface HostelDao {

    boolean add(Hostel hostel);

    List<Hostel> list();

    Hostel get(int id);

}
