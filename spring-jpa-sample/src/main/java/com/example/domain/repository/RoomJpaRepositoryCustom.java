package com.example.domain.repository;

import java.util.List;

import com.example.domain.model.Room;

public interface RoomJpaRepositoryCustom {
    List<Room> findByCriteria(RoomCriteria criteria);

    class RoomCriteria {
    }
}
