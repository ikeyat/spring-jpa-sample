package com.example.domain.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

/**
 * Created by ikeya on 15/09/06.
 */
@Data
@Entity
@Table(name = "room")
public class Room implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "room_id")
    private Integer roomId;
    
    @Column(name = "room_name")
    private String roomName;
    
    @Column(name = "capacity")
    private Integer capacity;
    
    @OneToMany(mappedBy = "room")
    private Set<Equipment> equipments;
}
