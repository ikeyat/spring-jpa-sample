package com.example.domain.repository;

import java.util.List;

import javax.persistence.LockModeType;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.domain.model.Room;

/**
 * Created by ikeya on 15/09/06.
 */
// public interface RoomJpaRepository extends JpaRepository<Room, Integer> { // (1)
public interface RoomJpaRepository extends JpaRepository<Room, Integer>, RoomJpaRepositoryCustom { // (1)
    // (2)
    
    @Query("SELECT r FROM Room r WHERE r.roomName = :roomName") // (1)
    List<Room> findByRoomName(@Param("roomName") String roomName); // (2)
    
    @Query("UPDATE Room r SET r.capacity = :capacity") // (3)
    Integer updateCapacityAll(@Param("capacity") Integer capacity); // (4)
    
    @Lock(LockModeType.PESSIMISTIC_WRITE) // (1)
    List<Room> findAll();
    
    @Query("SELECT r FROM Room r WHERE r.roomName = :roomName")
    Page<Room> findByRoomName(@Param("roomName") String roomName, Pageable pageable); // (1)

    @Query("SELECT DISTINCT r FROM Room r JOIN FETCH r.equipments ORDER BY r.roomId ASC")
    List<Room> findAllFetch();
}
