package com.example.domain.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.model.Room;

@Service
public class RoomServicePureJpaImpl {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly = true)
    public Room getRoom(Integer id) {
        Room room = entityManager.find(Room.class, id);
        return room;
    }
    
    @Transactional(readOnly = true)
    public List<Room> getRoomsAll() {
        String QUERY = "SELECT r FROM Room r";
        TypedQuery<Room> query = entityManager.createQuery(QUERY, Room.class);
        return query.getResultList();
    }
    
    @Transactional
    public void createRoom(Room room) {
        entityManager.persist(room);
    }
    
    @Transactional
    public void updateRoom(Integer id, String name, Integer capacity) {
        Room room = entityManager.find(Room.class, id);
        room.setName(name);
        room.setCapacity(capacity);
    }
    
    @Transactional
    public void updateRoom(Room room) {
        if (room.getId() == null) {
            // 不正な入力なので例外発生（略）
        }
        entityManager.merge(room);
    }
    
    @Transactional
    public void updateRoomWithPessimisticLock(Integer id, String name, Integer capacity) {
        Room room = entityManager.find(Room.class, id);
        entityManager.lock(room, LockModeType.PESSIMISTIC_READ); // PessimisticLockExceptionが発生
    }
    
    @Transactional
    public void updateRoomWithOptimisticLock(Integer id, String name, Integer capacity) {
        Room room = entityManager.find(Room.class, id);
        entityManager.lock(room, LockModeType.OPTIMISTIC); // OptimisticLockExceptionが発生
    }
}
