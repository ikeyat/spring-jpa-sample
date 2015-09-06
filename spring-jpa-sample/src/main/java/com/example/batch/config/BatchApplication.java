package com.example.batch.config;

import com.example.app.config.ApplicationConfig;
import com.example.domain.model.Room;
import com.example.domain.service.RoomService;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by ikeya on 15/09/06.
 */
public class BatchApplication {
    public static void main(String[] args) throws Exception {
        ApplicationContext context = new AnnotationConfigApplicationContext(BatchApplicationConfig.class, ApplicationConfig.class);
        RoomService roomService = context.getBean(RoomService.class);

        Room room1 = new Room();
        room1.setName("Hoge Room");
        room1.setCapacity(120);

        Room room2 = new Room();
        room2.setName("Fuga Room");
        room2.setCapacity(30);

        roomService.createRoom(room1);
        roomService.createRoom(room2);


        Room targetRoom = roomService.getRoom(room1.getId());
        System.out.println(ReflectionToStringBuilder.toString(targetRoom));
    }

}
