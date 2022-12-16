package com.java.roomDescription.controller;

import com.java.roomDescription.client.ClientAPI;
import com.java.roomDescription.model.Door;
import com.java.roomDescription.model.dto.DoorDTO;
import com.java.roomDescription.service.DoorServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/doors")
public class DoorController {
    final ModelMapper modelMapper = new ModelMapper();
    final DoorServiceImpl service;
    final ClientAPI clientAPI;

    public DoorController(DoorServiceImpl doorServiceImpl, ClientAPI clientAPI) {
        this.service = doorServiceImpl;
        this.clientAPI = clientAPI;
    }

    @GetMapping()
    public List<DoorDTO> getListDoor() {

        return convertToDTOList(service.getListDoor());
    }

    @GetMapping("/{room}")
    public List<DoorDTO> getDoorsByRoom(@PathVariable String room) {
        return convertToDTOList(service.getDoorsByRoom(room));
    }

    @GetMapping("/favorites")
    public List<DoorDTO> getDoorsByFavorites() {
        return convertToDTOList(service.getDoorsByFavorites());
    }

    @GetMapping("/favorite/{id}")
    public DoorDTO addDoorFavorites(@PathVariable Long id) {
        List<Door> doors = convertToEntityList(clientAPI.getInfoDoors().getData());
        return convertToDTO(service.addDoorFavorites(doors, id));
    }

    public DoorDTO convertToDTO(Door door) {
        modelMapper.typeMap(Door.class, DoorDTO.class).addMappings(mapper -> {
            mapper.map(src -> src.getRoom().getName(), DoorDTO::setRoom);
        });
        return modelMapper.map(door, DoorDTO.class);
    }

    public Door convertToEntity(DoorDTO doorDTO) {
        modelMapper.typeMap(DoorDTO.class, Door.class)
                .addMappings(mapper -> mapper.<String>map(src -> src.getRoom(), (dest, v) -> dest.getRoom().setName(v)));
        return modelMapper.map(doorDTO, Door.class);
    }

    public List<Door> convertToEntityList(List<DoorDTO> doorDTOList) {
        return doorDTOList.stream()
                .map(this::convertToEntity)
                .collect(Collectors.toList());
    }

    public List<DoorDTO> convertToDTOList(List<Door> doorList) {
        return doorList.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
}
