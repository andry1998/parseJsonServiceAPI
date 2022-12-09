package com.java.roomDescription.service;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RoomService {
    private final DoorService doorService;
    private final CameraService cameraService;

    public RoomService(DoorService doorService, CameraService cameraService) {
        this.doorService = doorService;
        this.cameraService = cameraService;
    }
    //получить список всех комнат
    public Set<String> getRooms() throws IOException {
        Set<String> rooms = new HashSet<>();
        rooms.addAll(doorService.getRoomDoors());
        rooms.addAll(cameraService.getRoomCameras());
        rooms.remove(null);
        return rooms;
    }
}
