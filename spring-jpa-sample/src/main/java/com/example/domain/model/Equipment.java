package com.example.domain.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
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
    private String  equipmentName;

    @ManyToOne
    @JoinColumn(name = "room_id_fk")
    private Room    room;

    @Column(name = "equipment_count")
    private Integer equipmentCount;

    @Column(name = "equipment_remarks")
    private String  equipmentRemarks;

//    @Override
//    public boolean equals(Object o) {
//        if (this == o)
//            return true;
//        if (o == null || getClass() != o.getClass())
//            return false;
//
//        Equipment equipment = (Equipment) o;
//
//        if (equipmentId != null ? !equipmentId.equals(equipment.equipmentId)
//                : equipment.equipmentId != null)
//            return false;
//        if (equipmentName != null ? !equipmentName
//                .equals(equipment.equipmentName)
//                : equipment.equipmentName != null)
//            return false;
//        if (room != null ? !room.equals(equipment.room)
//                : equipment.room != null)
//            return false;
//        if (equipmentCount != null ? !equipmentCount
//                .equals(equipment.equipmentCount)
//                : equipment.equipmentCount != null)
//            return false;
//        if (equipmentRemarks != null ? !equipmentRemarks
//                .equals(equipment.equipmentRemarks)
//                : equipment.equipmentRemarks != null)
//            return false;
//
//        return true;
//    }
//
//    @Override
//    public int hashCode() {
//        int result = equipmentId != null ? equipmentId.hashCode() : 0;
//        result = 31 * result
//                + (equipmentName != null ? equipmentName.hashCode() : 0);
//        result = 31 * result + (room != null ? room.hashCode() : 0);
//        result = 31 * result
//                + (equipmentCount != null ? equipmentCount.hashCode() : 0);
//        result = 31 * result
//                + (equipmentRemarks != null ? equipmentRemarks.hashCode() : 0);
//        return result;
//    }
}
