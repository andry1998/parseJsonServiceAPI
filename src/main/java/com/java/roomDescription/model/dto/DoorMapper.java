package com.java.roomDescription.model.dto;

import com.java.roomDescription.model.Door;
import com.java.roomDescription.repository.RoomRepository;

import java.util.List;
import java.util.stream.Collectors;

public class DoorMapper {

    public Door mapToEntity(DoorDTO dto, RoomRepository roomRepository) {
        Door door = new Door();
        door.setId(dto.getId());
        door.setName(dto.getName());
        door.setRoom(roomRepository.getRoomByName(dto.getRoom()));
        door.setSnapshot(dto.getSnapshot());
        door.setFavorites(dto.getFavorites());
        return door;
    }

    public List<Door> mapToEntityList(List<DoorDTO> dto, RoomRepository roomRepository) {
        return dto.stream()
                .map(dtoList -> mapToEntity(dtoList, roomRepository))
                .collect(Collectors.toList());
    }

    public DoorDTO mapToDTO(Door door) {
        DoorDTO dto = new DoorDTO();
        dto.setId(door.getId());
        dto.setName(door.getName());
        if(door.getRoom() != null)
            dto.setRoom(door.getRoom().getName());
        else
            dto.setRoom(null);
        dto.setSnapshot(door.getSnapshot());
        dto.setFavorites(door.getFavorites());
        return dto;
    }

    public List<DoorDTO> mapToDTOList(List<Door> doorList) {
        return doorList.stream()
                .map(door -> mapToDTO(door))
                .collect(Collectors.toList());
    }
}
