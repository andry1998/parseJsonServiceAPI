package com.java.roomDescription.controller;

import com.java.roomDescription.model.Door;
import com.java.roomDescription.model.dto.DoorDTO;
import com.java.roomDescription.model.dto.DoorMapper;
import com.java.roomDescription.service.DoorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/doors")
public class DoorController {

    final DoorMapper mapper = new DoorMapper();

    final DoorService<Door, Long> service;

    @GetMapping()
    public List<DoorDTO> getListDoor() {
        return mapper.mapToDTOList(service.getListDoor());
    }

    @GetMapping("/{room}")
    public List<DoorDTO> getDoorsByRoom(@PathVariable String room) {
        return mapper.mapToDTOList(service.getDoorsByRoom(room));
    }

    @GetMapping("/favorites")
    public List<DoorDTO> getDoorsByFavorites() {
        return mapper.mapToDTOList(service.getDoorsByFavorites());
    }

    @GetMapping("/favorite/{id}")
    public DoorDTO addDoorFavorites(@PathVariable Long id) {
        return mapper.mapToDTO(service.addDoorFavorites(id));
    }
}
