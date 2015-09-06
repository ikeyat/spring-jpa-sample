package com.example.domain.repository;

import com.example.domain.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

/**
 * Created by ikeya on 15/09/06.
 */
public interface RoomRepository {
    public Room findOne(Integer id);
    public Room save(Room room);
    public List<Room> findAll();
    public void delete(Room room);
}
