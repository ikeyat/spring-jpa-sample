package com.example.domain.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.example.domain.model.Room;

public class RoomJpaRepositoryImpl implements RoomJpaRepositoryCustom {
    
    @PersistenceContext
    private  EntityManager entityManager;
    
    public List<Room> findByCriteria(RoomCriteria criteria) {
        // 中略
        List<Room> rooms = null;
        
        return rooms;
    }
}
