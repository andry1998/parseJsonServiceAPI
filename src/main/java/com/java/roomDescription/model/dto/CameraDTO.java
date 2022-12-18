package com.java.roomDescription.model.dto;

import com.java.roomDescription.model.Camera;
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
public class CameraDTO {

    private Long id;

    private String name;

    private String snapshot;

    private String room;

    private Boolean favorites;

    private Boolean rec;

    public CameraDTO(Camera entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.room = entity.getRoom().getName();
        this.favorites = entity.getFavorites();
        this.snapshot = entity.getSnapshot();
    }

    public List<CameraDTO> convertToDTOList(List<Camera> entity) {
        return entity.stream()
                .map(CameraDTO::new)
                .collect(Collectors.toList());
    }

    public Camera convertToEntity(CameraDTO dto) {
        return setValueEntity(dto);
    }

    public List<Camera> convertToEntityList(List<CameraDTO> dto) {
        return dto.stream()
                .map(cameraDTO -> setValueEntity(cameraDTO))
                .collect(Collectors.toList());
    }

    public Camera setValueEntity(CameraDTO dto) {
//        Camera entity = new Camera(
//                dto.getId(),
//                dto.getName(),
//                new Room(dto.getRoom()),
//                dto.getSnapshot(),
//                dto.getFavorites(),
//                dto.getRec()
//        );
        Camera entity = new Camera();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setRoom(new Room(dto.getRoom()));
        entity.setFavorites(dto.getFavorites());
        entity.setRec(dto.getRec());
        return entity;
    }
}
