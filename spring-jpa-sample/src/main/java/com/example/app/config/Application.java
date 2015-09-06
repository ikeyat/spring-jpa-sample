package com.example.app.config;

import com.example.domain.model.Room;
import com.example.domain.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by ikeya on 15/09/06.
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.example.app")
public class Application implements CommandLineRunner {

    @Autowired
    RoomRepository roomRepository;

    @Override
    @Transactional
    public void run(String... strings) throws Exception {
        Room room1 = new Room();
        room1.setName("Hoge Room");
        room1.setCapacity(120);

        Room room2 = new Room();
        room2.setName("Fuga Room");
        room2.setCapacity(30);

        roomRepository.save(room1);
        roomRepository.save(room2);
    }

    public static void main(String[] args) throws Exception {

        SpringApplication.run(Application.class, args);
    }
}
