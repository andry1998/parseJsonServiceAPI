package com.java.roomDescription.model.dto;

import com.java.roomDescription.model.Room;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoomDTO {
    String name;

    public Room convertToEntity(RoomDTO dto) {
        return new Room(dto.name);
    }
}
