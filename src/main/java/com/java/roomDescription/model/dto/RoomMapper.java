package com.java.roomDescription.model.dto;

import com.java.roomDescription.model.Room;

import java.util.List;
import java.util.stream.Collectors;

public class RoomMapper {

    public Room mapToEntity(RoomDTO dto) {
        return new Room(dto.getName());
    }

    public List<Room> mapToEntityList(List<RoomDTO> dtoList) {
        return dtoList.stream()
                .map(this::mapToEntity)
                .collect(Collectors.toList());
    }

    public RoomDTO mapToDTO(Room dto) {
        return new RoomDTO(dto.getName());
    }

    public List<RoomDTO> mapToDTOList(List<Room> dtoList) {
        return dtoList.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
}
