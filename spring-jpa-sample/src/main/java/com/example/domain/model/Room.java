package com.example.domain.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;

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
    private Integer        roomId;

    @Column(name = "room_name")
    private String         roomName;

    @Column(name = "capacity")
    private Integer        capacity;

    @OneToMany(mappedBy = "room")
    // @OneToMany
    // @JoinColumn(name = "room_id_fk")
    private Set<Equipment> equipments;

    @Version
    @Column(name = "room_version")
    private Integer roomVersion;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Room room = (Room) o;

        if (roomId != null ? !roomId.equals(room.roomId) : room.roomId != null)
            return false;
        if (roomName != null ? !roomName.equals(room.roomName)
                : room.roomName != null)
            return false;
        if (capacity != null ? !capacity.equals(room.capacity)
                : room.capacity != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = roomId != null ? roomId.hashCode() : 0;
        result = 31 * result + (roomName != null ? roomName.hashCode() : 0);
        result = 31 * result + (capacity != null ? capacity.hashCode() : 0);
        return result;
    }
}
