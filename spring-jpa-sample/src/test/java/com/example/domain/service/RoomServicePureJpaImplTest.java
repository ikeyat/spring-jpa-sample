package com.example.domain.service;

import com.example.app.config.ApplicationConfig;
import com.example.batch.config.BatchApplicationConfig;
import com.example.domain.model.Room;
import org.hibernate.StaleObjectStateException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.OptimisticLockException;
import java.util.concurrent.*;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by ikeya on 16/06/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {BatchApplicationConfig.class, ApplicationConfig.class})
public class RoomServicePureJpaImplTest {

    @Autowired
    RoomServicePureJpaImpl roomService;

    @Test
    public void testOptimisticLock() throws InterruptedException, ExecutionException {
        final Room room1 = new Room();
        room1.setRoomName("Hoge Room");
        room1.setCapacity(120);

        final Room room2 = new Room();
        room2.setRoomName("Fuga Room");
        room2.setCapacity(30);

        roomService.createRoom(room1);
        roomService.createRoom(room2);


        ExecutorService executor = Executors.newFixedThreadPool(10);
        Future<?> f1 = executor.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    roomService.updateRoomWithOptimisticLock(room1.getRoomId(),
                            "Hoge Room Modified by thread 1", 0);
                } catch (Exception e) {
                    e.printStackTrace();
                    assertThat(e, instanceOf(ObjectOptimisticLockingFailureException.class));
                    assertThat(e.getCause(), instanceOf(StaleObjectStateException.class));
                }
            }
        });
        Future<?> f2 = executor.submit(new Runnable() {
            @Override
            public void run() {
                Room room = roomService.getRoom(room1.getRoomId());
                room.setRoomName("hoge");
                roomService.updateRoom(room);
            }
        });
        f1.get();
        f2.get();
    }

    @Test
    public void testOptimisticLockWithFlush() throws InterruptedException, ExecutionException {
        final Room room1 = new Room();
        room1.setRoomName("Hoge Room");
        room1.setCapacity(120);

        final Room room2 = new Room();
        room2.setRoomName("Fuga Room");
        room2.setCapacity(30);

        roomService.createRoom(room1);
        roomService.createRoom(room2);


        ExecutorService executor = Executors.newFixedThreadPool(10);
        Future<?> f1 = executor.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    roomService.updateAndFlushRoomWithOptimisticLock(room1.getRoomId(),
                            "Hoge Room Modified by thread 1", 0);
                } catch (Exception e) {
                    e.printStackTrace();
                    assertThat(e, instanceOf(OptimisticLockException.class));
                    assertThat(e.getCause(), instanceOf(StaleObjectStateException.class));
                }
            }
        });
        Future<?> f2 = executor.submit(new Runnable() {
            @Override
            public void run() {
                Room room = roomService.getRoom(room1.getRoomId());
                room.setRoomName("hoge");
                roomService.updateRoom(room);
            }
        });
        f1.get();
        f2.get();
    }
}
