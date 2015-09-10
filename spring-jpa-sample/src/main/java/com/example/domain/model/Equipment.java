package com.example.domain.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "equipment")
public class Equipment implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "equipment_id")
    private Integer equipmentId;
    
    @Column(name = "equipment_name")
    private String equipmentName;
    
    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;
    
    @Column(name = "equipment_count")
    private Integer equipmentCount;
    
    @Column(name = "equipment_remarks")
    private String equipmentRemarks;

}
