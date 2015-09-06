package com.example.domain.service;

import com.example.domain.model.Room;

import java.util.Collection;
import java.util.List;

/**
 * Created by ikeya on 15/09/06.
 */
public interface RoomService {
    public Room getRoom(Integer id);
    public void createRoom(Room room);
    public List<Room> getRoomsAll();
    public void deleteRoom(Integer id);
}
