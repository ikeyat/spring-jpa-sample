package com.example.domain.repository;

import java.util.List;

import com.example.domain.model.Room;

/**
 * Created by ikeya on 15/09/06.
 */
public interface RoomRepository {
    public Room find(Integer id);
    public void persist(Room room);
    public Room merge(Room room);
    public List<Room> findAll();
    public void remove(Room room);
}
