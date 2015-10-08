package com.example.domain.service;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.model.Room;
import com.example.domain.repository.RoomJpaRepository;

/**
 * Created by ikeya on 15/09/06.
 */
@Service
@Profile("!without-datajpa")
@Slf4j
public class RoomServiceJpaImpl implements RoomService {
    @Autowired
    private RoomJpaRepository roomRepository; // (1)

    @Transactional(readOnly = true)
    // (2)
    public Room getRoom(Integer id) {
        Room room = roomRepository.findOne(id); // (3)
        if (room == null) {
            // 対象のroomが存在しない場合の処理（省略）
        }
        return room;
    }

    @Transactional(readOnly = true)
    public List<Room> getRoomsAll() {
        // return roomRepository.findAll(new Sort(Direction.ASC, "roomId")); // (4)
        return roomRepository.findAllFetch();
    }

    @Transactional
    public Room createRoom(String roomName, Integer capacity) {
        Room room = new Room();
        room.setRoomName(roomName);
        room.setCapacity(capacity);
        return roomRepository.save(room); // (5)
    }

    @Transactional
    public Room updateRoomName(Integer id, String roomName) {
        Room room = getRoom(id); // (6)
        room.setRoomName(roomName);
        return room;
    }

    @Transactional
    public void deleteRoom(Integer id) {
        roomRepository.delete(id); // (7)
    }

    @Override
    @Transactional
    public void createRoom(Room room) {
        roomRepository.save(room);
    }

    @Transactional
    public List<Room> getRoomsAll(int page, int size) {
        Sort sort = new Sort(Direction.ASC, "roomName"); // (1)
        Pageable pageable = new PageRequest(page, size, sort); // (2)
        Page<Room> rooms = roomRepository.findAll(pageable); // (3)
        return rooms.getContent(); // (4)
    }

    @Transactional
    public Page<Room> getRoomsAll(Pageable pageable) {
        Page<Room> rooms = roomRepository.findAll(pageable); // (x)
        return rooms;
    }
}
