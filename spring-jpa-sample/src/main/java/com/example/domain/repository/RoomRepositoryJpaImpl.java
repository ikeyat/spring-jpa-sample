package com.example.domain.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.example.domain.model.Room;

/**
 * Created by ikeya on 15/09/06.
 */
@Repository
public class RoomRepositoryJpaImpl implements RoomRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Room find(Integer id) {
        Room room = entityManager.find(Room.class, id);
        return room;
    }

    @Override
    public void persist(Room room) {
        entityManager.persist(room);
        entityManager.flush();
    }
    
    @Override
    public Room merge(Room room) {
        return entityManager.merge(room);
    }

    @Override
    public List<Room> findAll() {
        String QUERY = "SELECT r FROM Room r";
        TypedQuery<Room> query = entityManager.createQuery(QUERY, Room.class);
        return query.getResultList();
    }

    @Override
    public void remove(Room room) {
        entityManager.remove(room);
    }
}
