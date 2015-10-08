package com.example.domain.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.example.domain.model.Equipment;

/**
 * Created by ikeya on 15/09/06.
 */
@Repository
public class EquipmentRepositoryJpaImpl implements EquipmentRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Equipment find(Integer id) {
        Equipment equipment = entityManager.find(Equipment.class, id, LockModeType.PESSIMISTIC_WRITE);
        return equipment;
    }

    @Override
    public void persist(Equipment equipment) {
        entityManager.persist(equipment);
        entityManager.flush();
    }

    @Override
    public Equipment merge(Equipment equipment) {
        return entityManager.merge(equipment);
    }

    @Override
    public List<Equipment> findAll() {
        String QUERY = "SELECT e FROM Equipment e";
        TypedQuery<Equipment> query = entityManager.createQuery(QUERY,
                Equipment.class);
        return query.getResultList();
    }

    @Override
    public void remove(Equipment equipment) {
        entityManager.remove(equipment);
    }
}
