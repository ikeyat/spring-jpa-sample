package com.example.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.domain.model.Room;

/**
 * Created by ikeya on 15/09/06.
 */
public interface RoomJpaRepository extends JpaRepository<Room, Integer> {
}
