package com.java.roomDescription.controller;

import com.java.roomDescription.model.Room;
import com.java.roomDescription.model.dto.RoomDTO;
import com.java.roomDescription.model.dto.RoomMapper;
import com.java.roomDescription.service.RoomService;
import com.java.roomDescription.service.RoomServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rooms")
public class RoomController {
    final RoomService<Room, Long> roomService;
    public RoomController(RoomServiceImpl roomService) {
        this.roomService = roomService;
    }

    @GetMapping()
    public List<RoomDTO> getRooms() {
        return new RoomMapper().mapToDTOList(roomService.getNameRooms());
    }
}
