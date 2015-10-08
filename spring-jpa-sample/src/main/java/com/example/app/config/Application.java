package com.example.app.config;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.model.Equipment;
import com.example.domain.model.Room;
import com.example.domain.repository.EquipmentRepository;
import com.example.domain.repository.RoomRepository;

/**
 * Created by ikeya on 15/09/06.
 */
@SpringBootApplication
@ComponentScan(basePackages = { "com.example.app", "com.example.domain" })
@Slf4j
public class Application implements CommandLineRunner {

    @Autowired
    RoomRepository         roomRepository;

    @Autowired
    EquipmentRepository    equipmentRepository;

//    @Autowired
//    RoomServicePureJpaImpl roomService;

    @Override
    @Transactional
    public void run(String... strings) throws Exception {
        Room room1 = new Room();
        room1.setRoomName("Hoge Room");
        room1.setCapacity(120);

        Room room2 = new Room();
        room2.setRoomName("Fuga Room");
        room2.setCapacity(30);

        Room room3 = new Room();
        room3.setRoomName("Fuga Room");
        room3.setCapacity(10);

        Equipment equipment1 = new Equipment();
        equipment1.setEquipmentName("White board");
        equipment1.setEquipmentCount(1);
        equipment1.setRoom(room1);

        Equipment equipment2 = new Equipment();
        equipment2.setEquipmentName("Projector");
        equipment2.setEquipmentCount(2);
        equipment2.setRoom(room1);

        Equipment equipment3 = new Equipment();
        equipment3.setEquipmentName("Black board");
        equipment3.setEquipmentCount(1);
        equipment3.setRoom(room1);

        roomRepository.persist(room1);
        roomRepository.persist(room2);
        roomRepository.persist(room3);

        equipmentRepository.persist(equipment1);
        equipmentRepository.persist(equipment2);
        equipmentRepository.persist(equipment3);
        
        for (int i = 0; i < 100; i++) {
            Room genRoom = new Room();
            genRoom.setRoomName("Generated Room " + i);
            genRoom.setCapacity(100);
            roomRepository.persist(genRoom);
        }
        
/*
        List<Room> rooms = roomService.getRoomsByName("Fuga Room");
        for (Room room : rooms) {
            log.info("room id for \"Fuga Room\": {}", room.getRoomId());
        }
        Executor executor = Executors.newFixedThreadPool(10);
        executor.execute(new Runnable() {
            @Override
            public void run() {
                // roomService.updateRoomWithOptimisticLock(room1.getRoomId(),
                // "Hoge Room Modified by thread 1", 0);
                roomService.updateRoomWithPessimisticLock(1,
                        "Hoge Room Modified by thread 1", 0);
            }
        });

        executor.execute(new Runnable() {
            @Override
            public void run() {
                // roomService.updateRoomWithOptimisticLock(room1.getRoomId(),
                // "Hoge Room Modified by thread 2", 1000);
                // roomService.updateRoomWithPessimisticLock(room1.getRoomId(),
                // "Hoge Room Modified by thread 2", 1000);
                Room room = roomService.getRoom(1);
                room.setRoomName("hoge");
                roomService.updateRoom(room);
            }
        });
*/
    }

    public static void main(String[] args) throws Exception {

        SpringApplication.run(Application.class, args);
    }
}
