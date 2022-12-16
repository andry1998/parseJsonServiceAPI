package com.java.roomDescription.controller;

import com.java.roomDescription.client.ClientAPI;
import com.java.roomDescription.model.Camera;
import com.java.roomDescription.model.dto.CameraDTO;
import com.java.roomDescription.service.CameraServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cameras")
public class CameraController {
    final CameraServiceImpl service;
    final ClientAPI clientAPI;
    final ModelMapper modelMapper = new ModelMapper();
    public CameraController(CameraServiceImpl service, ClientAPI clientAPI) {
        this.service = service;
        this.clientAPI = clientAPI;
    }

    @GetMapping()
    public List<CameraDTO> getListCamera() {
        return convertToDTOList(service.getListCamera());
    }

    @GetMapping("/{room}")
    public List<CameraDTO> getCamerasByRoom(@PathVariable String room) {
        return convertToDTOList(service.getCamerasInRoom(room));
    }

    @GetMapping("/favorites")
    public List<CameraDTO> getCamerasByFavorites() {
        return convertToDTOList(service.getCamerasByFavorites());
    }

    @GetMapping("/favorite/{id}")
    public CameraDTO addCameraFavorites(@PathVariable Long id) {
        List<Camera> cameras = convertToEntityList(clientAPI.getInfoCameras().getData().getCameras());
        return convertToDTO(service.addCameraFavorites(cameras, id));
    }

    @GetMapping("/start/{id}")
    public CameraDTO startCameraRecording(@PathVariable Long id) {
        List<Camera> cameras = convertToEntityList(clientAPI.getInfoCameras().getData().getCameras());
        return convertToDTO(service.startCameraRecording(cameras, id));
    }

    @GetMapping("/stop/{id}")
    public CameraDTO stopCameraRecording(@PathVariable Long id) {
        List<Camera> cameras = convertToEntityList(clientAPI.getInfoCameras().getData().getCameras());
        return convertToDTO(service.stopCameraRecording(cameras, id));
    }

    public CameraDTO convertToDTO(Camera camera) {
        modelMapper.typeMap(Camera.class, CameraDTO.class).addMappings(mapper -> {
            mapper.map(src -> src.getRoom().getName(), CameraDTO::setRoom);
        });
        return modelMapper.map(camera, CameraDTO.class);
    }

    public Camera convertToEntity(CameraDTO cameraDTO) {
        modelMapper.typeMap(CameraDTO.class, Camera.class)
                .addMappings(mapper -> mapper.<String>map(src -> src.getRoom(), (dest, v) -> dest.getRoom().setName(v)));
        return modelMapper.map(cameraDTO, Camera.class);
    }

    public List<Camera> convertToEntityList(List<CameraDTO> cameraDTOList) {
        return cameraDTOList.stream()
                    .map(this::convertToEntity)
                    .collect(Collectors.toList());
    }

    public List<CameraDTO> convertToDTOList(List<Camera> cameraList) {
        return cameraList.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

}
