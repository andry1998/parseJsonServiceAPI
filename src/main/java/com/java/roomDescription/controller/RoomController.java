package com.java.roomDescription.controller;

import com.java.roomDescription.model.Room;
import com.java.roomDescription.service.RoomServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rooms")
public class RoomController {
    final RoomServiceImpl roomService;
    public RoomController(RoomServiceImpl roomService) {
        this.roomService = roomService;
    }

    @GetMapping()
    public List<Room> getRooms() {
        return roomService.getListRoom();
    }
}
