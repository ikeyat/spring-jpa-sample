package com.example.domain.service;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.LockTimeoutException;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceContext;
import javax.persistence.PessimisticLockException;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.model.Equipment;
import com.example.domain.model.Room;

@Service
public class RoomServicePureJpaImpl {
    @PersistenceContext
    private EntityManager entityManager; // (1)

    @Transactional(readOnly = true)
    // (2)
    public Room getRoom(Integer id) {
        Room room = entityManager.find(Room.class, id); // (3)
        if (room == null) {
            // 対象のroomが存在しない場合の処理（省略）
        }
        return room;
    }

    @Transactional
    public Room createRoom(String roomName, Integer capacity) {
        Room room = new Room();
        room.setRoomName(roomName);
        room.setCapacity(capacity);
        entityManager.persist(room); // (4)
        return room;
    }

    @Transactional
    public Room updateRoomName(Integer id, String roomName) {
        Room room = getRoom(id); // (5)
        room.setRoomName(roomName);
        return room;
    }

    @Transactional
    public void deleteRoom(Integer id) {
        Room room = getRoom(id); // (6)
        entityManager.remove(room); // (7)
    }

    @Transactional(readOnly = true)
    public List<Room> getRoomsAll() {
        String QUERY = "SELECT r FROM Room r ORDER BY r.roomId ASC"; // (4)
        TypedQuery<Room> query = entityManager.createQuery(QUERY, Room.class); // (4)
        return query.getResultList(); // (4)
    }

    @Transactional
    public void updateRoomWithPessimisticLock(Integer id, String roomName,
            Integer capacity) {
        Room room = entityManager.find(Room.class, id);
        try {
            entityManager.lock(room, LockModeType.PESSIMISTIC_READ); // (1)
        } catch (PessimisticLockException e) { // (2)
            // ロック取得に失敗
            // 中略...
            e.printStackTrace();
        } catch (LockTimeoutException e) { // (3)
            // ロック取得にタイムアウト（トランザクション自体はロールバックされない）
            // 中略...
            e.printStackTrace();
        }
        // 更新処理（省略）
        sleep(10000L);
        room.setRoomName(roomName);
        room.setCapacity(capacity);

    }

    @Transactional
    public void updateRoomWithOptimisticLock(Integer id, String roomName,
            Integer capacity) {
        Room room = entityManager.find(Room.class, id);
        entityManager.lock(room, LockModeType.OPTIMISTIC); // (1)
        // 更新処理（省略）
        // 楽観ロック失敗時はトランザクション終了時にOptimisticLockExceptionが発生する

        sleep(10000L);
        room.setRoomName(roomName);
        room.setCapacity(capacity);

    }

    @Transactional(readOnly = true)
    public List<Equipment> getEquipmentsInRoom(Integer roomId) {
        Room room = entityManager.find(Room.class, roomId);
        return room.getEquipments();
    }

    @Transactional(readOnly = true)
    public Room getRoomOfEquipment(Integer equipmentId) {
        Equipment equipment = entityManager.find(Equipment.class, equipmentId);
        return equipment.getRoom();
    }

    @Transactional(readOnly = true)
    public List<Room> getRoomsByName(String roomName) {
        String QUERY = "SELECT r FROM Room r WHERE r.roomName = :roomName"; // (1)
        TypedQuery<Room> query = entityManager.createQuery(QUERY, Room.class); // (2)
        query.setParameter("roomName", roomName); // (3)
        return query.getResultList(); // (4)
    }

    @Transactional(readOnly = true)
    public List<Room> getRoomsByNameFetch(String roomName) {
        String QUERY = "SELECT DISTINCT r FROM Room r LEFT JOIN r.equipments WHERE r.roomName = :roomName"; // (1)
        TypedQuery<Room> query = entityManager.createQuery(QUERY, Room.class); // (2)
        query.setParameter("roomName", roomName); // (3)
        return query.getResultList(); // (4)
    }

    @Transactional
    public Integer updateCapacityAll(Integer capacity) {
        String QUERY = "UPDATE Room r SET r.capacity = :capacity"; // (5)
        TypedQuery<Room> query = entityManager.createQuery(QUERY, Room.class); // (6)
        query.setParameter("capacity", capacity); // (7)
        return query.executeUpdate(); // (8)
    }

    // @Transactional
    // public void addRoomOfEquipment(Integer roomId, Equipment equipment) {
    // List<Equipment> equipments = room.getEquipments();
    // equipments.add(equipment); // (5)
    // equipment.setRoom(room); // (6)
    // }

    private void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
