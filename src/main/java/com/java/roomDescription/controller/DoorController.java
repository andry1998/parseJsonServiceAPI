package com.java.roomDescription.controller;

import com.java.roomDescription.model.Door;
import com.java.roomDescription.service.DoorServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doors")
public class DoorController {
    final DoorServiceImpl service;

    public DoorController(DoorServiceImpl doorServiceImpl) {
        this.service = doorServiceImpl;
    }

    @GetMapping()
    public List<Door> getListDoor() {
        return service.getListDoor();
    }

    @GetMapping("/{room}")
    public List<Door> getDoorsByRoom(@PathVariable String room) {
        return service.getDoorsByRoom(room);
    }

    @GetMapping("/favorites")
    public List<Door> getDoorsByRoom() {
        return service.getDoorsByFavorites();
    }

    @GetMapping("/favorite/{id}")
    public Door addDoorFavorites(@PathVariable int id) {
        return service.addDoorFavorites(id);
    }
}
