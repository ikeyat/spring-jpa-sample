package com.example.domain.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.domain.model.Room;

/**
 * Created by ikeya on 15/09/06.
 */
public interface RoomService {
    public Room getRoom(Integer id);
    public void createRoom(Room room);
    public List<Room> getRoomsAll();
    public void deleteRoom(Integer id);
    public List<Room> getRoomsAll(int page, int size);
    public Page<Room> getRoomsAll(Pageable page);
}
