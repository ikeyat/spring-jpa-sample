package com.example.domain.repository;

import com.example.domain.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ikeya on 15/09/06.
 */
@Repository
public interface RoomJpaRepository extends JpaRepository<Room, Integer> {
}
