package com.example.domain.model;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import lombok.Data;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * Created by ikeya on 15/09/06.
 */
@Data
@Entity
@Table(name = "room")
@EntityListeners(AuditingEntityListener.class)
public class Room implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "room_id")
    private Integer        roomId;

    @Column(name = "room_name")
    private String         roomName;

    @Column(name = "capacity")
    private Integer        capacity;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    // @OneToMany
    // @JoinColumn(name = "room_id_fk")
    private List<Equipment> equipments;
    
    @Version
    @Column(name = "room_version")
    private Integer roomVersion;
    
    @CreatedBy // データの作成者
    @Column(name = "created_by")
    private String createdBy;
    
    @CreatedDate // データの作成日時
    @Column(name = "created_date")
    private LocalTime createdDate;
    
    @LastModifiedBy // データの最終更新者
    @Column(name = "last_modified_by")
    private String lastModifiedBy;
    
    @LastModifiedDate // データの最終更新日時
    @Column(name = "last_modified_date")
    private LocalTime lastModifiedDate;

//    @Override
//    public boolean equals(Object o) {
//        if (this == o)
//            return true;
//        if (o == null || getClass() != o.getClass())
//            return false;
//
//        Room room = (Room) o;
//
//        if (roomId != null ? !roomId.equals(room.roomId) : room.roomId != null)
//            return false;
//        if (roomName != null ? !roomName.equals(room.roomName)
//                : room.roomName != null)
//            return false;
//        if (capacity != null ? !capacity.equals(room.capacity)
//                : room.capacity != null)
//            return false;
//
//        return true;
//    }
//
//    @Override
//    public int hashCode() {
//        int result = roomId != null ? roomId.hashCode() : 0;
//        result = 31 * result + (roomName != null ? roomName.hashCode() : 0);
//        result = 31 * result + (capacity != null ? capacity.hashCode() : 0);
//        return result;
//    }
}
