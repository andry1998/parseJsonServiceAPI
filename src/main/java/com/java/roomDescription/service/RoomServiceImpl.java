package com.java.roomDescription.service;

import com.google.gson.Gson;
import com.java.roomDescription.client.CameraClient;
import com.java.roomDescription.client.DoorClient;
import com.java.roomDescription.model.Room;
import com.java.roomDescription.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RoomServiceImpl implements RoomService{
    final DoorClient doorClient;
    final CameraClient cameraClient;
    final RoomRepository roomRepository;

    public RoomServiceImpl(DoorClient doorClient, CameraClient cameraClient, RoomRepository roomRepository) {
        this.doorClient = doorClient;
        this.cameraClient = cameraClient;
        this.roomRepository = roomRepository;
    }

    @Override
    public void roomSynchronization()  {
        Gson gson = new Gson();
        Set<Room> rooms = new HashSet<>();
        rooms.addAll(doorClient.getInfoDoors().getData().stream()
                .map(door -> new Room(door.getRoom())).collect(Collectors.toSet()));
        rooms.addAll(cameraClient.getInfoCameras().getData().getRoom().stream()
                .map(room -> new Room(room))
                .collect(Collectors.toSet()));
        rooms.remove(new Room(null));
        roomRepository.saveAll(rooms);
    }

    @Override
    public List getListRoom() {
        return roomRepository.findAll();
    }

}
