package com.example.domain.repository;

import com.example.domain.model.Room;
import org.springframework.context.annotation.Profile;
import org.springframework.data.annotation.Persistent;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by ikeya on 15/09/06.
 */
@Repository
public class RoomRepositoryJpaImpl implements RoomRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Room findOne(Integer id) {
        Room room = entityManager.find(Room.class, id);
        return room;
    }

    @Override
    public Room save(Room room) {
        if (room.getId() == null) {
            entityManager.persist(room);
            return room;
        } else {
            return entityManager.merge(room);
        }
    }

    @Override
    public List<Room> findAll() {
        String QUERY = "SELECT r FROM Room r";
        TypedQuery<Room> query = entityManager.createQuery(QUERY, Room.class);
        return query.getResultList();
    }

    @Override
    public void delete(Room room) {
        entityManager.remove(room);
    }
}
