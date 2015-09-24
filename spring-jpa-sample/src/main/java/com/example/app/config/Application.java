package com.example.app.config;

import java.util.Set;

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
@ComponentScan(basePackages = "com.example.app")
@Slf4j
public class Application implements CommandLineRunner {

    @Autowired
    RoomRepository      roomRepository;

    @Autowired
    EquipmentRepository equipmentRepository;

    @Override
    @Transactional
    public void run(String... strings) throws Exception {
        Room room1 = new Room();
        room1.setRoomName("Hoge Room");
        room1.setCapacity(120);

        Room room2 = new Room();
        room2.setRoomName("Fuga Room");
        room2.setCapacity(30);

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
        
        equipmentRepository.persist(equipment1);
        equipmentRepository.persist(equipment2);
        equipmentRepository.persist(equipment3);

//        Set<Equipment> equipmentsRoom1 = room1.getEquipments();
//        for (Equipment equipment : equipmentsRoom1) {
//            log.info(equipment.getEquipmentName());
//        }
    }

    public static void main(String[] args) throws Exception {

        SpringApplication.run(Application.class, args);
    }
}
