package com.example.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.model.Room;
import com.example.domain.repository.RoomRepository;

/**
 * Created by ikeya on 15/09/06.
 */
@Service
@Profile("without-datajpa")
public class RoomServiceImpl implements RoomService {
    @Autowired
    private RoomRepository roomRepository;

    @Override
    @Transactional(readOnly = true)
    public Room getRoom(Integer id) {
        Room room = roomRepository.find(id);
        return room;
    }

    @Override
    @Transactional
    public void createRoom(Room room) {
        roomRepository.persist(room);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Room> getRoomsAll() {
        return roomRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteRoom(Integer id) {
        Room room = roomRepository.find(id);
        roomRepository.remove(room);
    }
}
