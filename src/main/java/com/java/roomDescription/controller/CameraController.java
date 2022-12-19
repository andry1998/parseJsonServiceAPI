package com.java.roomDescription.controller;

import com.java.roomDescription.client.ClientAPI;
import com.java.roomDescription.model.Camera;
import com.java.roomDescription.model.dto.CameraDTO;
import com.java.roomDescription.model.dto.CameraMapper;
import com.java.roomDescription.service.CameraService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/cameras")
public class CameraController {

    final CameraMapper mapper = new CameraMapper();

    final CameraService<Camera, Long> service;

    final ClientAPI clientAPI;

    @GetMapping()
    public List<CameraDTO> getListCamera() {
        return mapper.mapToDTOList(service.getListCamera());
    }

    @GetMapping("/{room}")
    public List<CameraDTO> getCamerasByRoom(@PathVariable String room) {
        return mapper.mapToDTOList(service.getCamerasInRoom(room));
    }

    @GetMapping("/favorites")
    public List<CameraDTO> getCamerasByFavorites() {
        return mapper.mapToDTOList(service.getCamerasByFavorites());
    }

    @GetMapping("/favorite/{id}")
    public CameraDTO addCameraFavorites(@PathVariable Long id) {
        return mapper.mapToDTO(service.addCameraFavorites(id));
    }

    @GetMapping("/start/{id}")
    public CameraDTO startCameraRecording(@PathVariable Long id) {
        return mapper.mapToDTO(service.startCameraRecording(id));
    }

    @GetMapping("/stop/{id}")
    public CameraDTO stopCameraRecording(@PathVariable Long id) {
        return mapper.mapToDTO(service.stopCameraRecording(id));
    }

}
