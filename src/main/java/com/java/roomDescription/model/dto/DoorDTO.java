package com.java.roomDescription.model.dto;

import com.java.roomDescription.model.Door;
import com.java.roomDescription.model.Room;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class DoorDTO {

    Long id;

    String name;

    String room;

    String snapshot;

    Boolean favorites;

    public DoorDTO(Door entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.room = entity.getRoom().getName();
        this.favorites = entity.getFavorites();
        this.snapshot = entity.getSnapshot();
    }

    public List<DoorDTO> convertToDTOList(List<Door> entity) {
        return entity.stream()
                .map(DoorDTO::new)
                .collect(Collectors.toList());
    }

    public Door convertToEntity(DoorDTO dto) {
        return setValueEntity(dto);
    }

    public List<Door> convertToEntityList(List<DoorDTO> dto) {
        return dto.stream()
                .map(doorDTO -> setValueEntity(doorDTO))
                .collect(Collectors.toList());
    }

    public Door setValueEntity(DoorDTO dto) {
        Door entity = new Door();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setRoom(new Room(dto.getRoom()));
        entity.setFavorites(dto.getFavorites());
        entity.setSnapshot(dto.getSnapshot());
        return entity;
    }
}
