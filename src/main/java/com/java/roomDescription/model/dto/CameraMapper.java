package com.java.roomDescription.model.dto;

import com.java.roomDescription.model.Camera;
import com.java.roomDescription.repository.RoomRepository;

import java.util.List;
import java.util.stream.Collectors;


public class CameraMapper {

    public Camera mapToEntity(CameraDTO dto, RoomRepository roomRepository) {
        Camera camera = new Camera();
        camera.setId(dto.getId());
        camera.setName(dto.getName());
        camera.setRoom(roomRepository.getRoomByName(dto.getRoom()));
        camera.setSnapshot(dto.getSnapshot());
        camera.setFavorites(dto.getFavorites());
        camera.setRec(dto.getRec());
        return camera;
    }

    public List<Camera> mapToEntityList(List<CameraDTO> dtoList, RoomRepository roomRepository) {
        return dtoList.stream()
                .map(dto -> mapToEntity(dto, roomRepository))
                .collect(Collectors.toList());
    }

    public CameraDTO mapToDTO(Camera camera) {
        CameraDTO dto = new CameraDTO();
        dto.setId(camera.getId());
        dto.setName(camera.getName());
        if(camera.getRoom() != null)
            dto.setRoom(camera.getRoom().getName());
        else
            dto.setRoom(null);
        dto.setSnapshot(camera.getSnapshot());
        dto.setFavorites(camera.getFavorites());
        dto.setRec(camera.getRec());
        return dto;
    }

    public List<CameraDTO> mapToDTOList(List<Camera> cameraList) {
        return cameraList.stream()
                .map(camera -> mapToDTO(camera))
                .collect(Collectors.toList());
    }
}
