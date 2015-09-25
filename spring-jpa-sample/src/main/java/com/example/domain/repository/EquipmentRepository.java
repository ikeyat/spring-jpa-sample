package com.example.domain.repository;

import java.util.List;

import com.example.domain.model.Equipment;

/**
 * Created by ikeya on 15/09/06.
 */
public interface EquipmentRepository {
    public Equipment find(Integer id);
    public void persist(Equipment equipment);
    public Equipment merge(Equipment equipment);
    public List<Equipment> findAll();
    public void remove(Equipment equipment);
}
