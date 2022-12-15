package com.java.roomDescription.controller;

import com.java.roomDescription.client.ClientAPI;
import com.java.roomDescription.model.Camera;
import com.java.roomDescription.model.dto.CameraDTO;
import com.java.roomDescription.service.CameraServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cameras")
public class CameraController {
    final CameraServiceImpl service;
    final ModelMapper modelMapper = new ModelMapper();
    final ClientAPI clientAPI;

    public CameraController(CameraServiceImpl service, ClientAPI clientAPI) {
        this.service = service;

        this.clientAPI = clientAPI;
    }

    @GetMapping()
    public List<CameraDTO> getListCamera() {
        return service.getListCamera().stream()
                .map(camera -> service.convertToDTO(camera))
                .collect(Collectors.toList());
        //return service.getListCamera();
    }

    @GetMapping("/{room}")
    public List<Camera> getCamerasByRoom(@PathVariable String room) {
        return service.getCamerasByRoom(room);
    }

    @GetMapping("/favorites")
    public List<Camera> getCamerasByFavorites() {
        return service.getCamerasByFavorites();
    }

    @GetMapping("/favorite/{id}")
    public Camera addCameraFavorites(@PathVariable Long id) {
        return service.addCameraFavorites(id);
    }

    @GetMapping("/start/{id}")
    public Camera startCameraRecording(@PathVariable Long id) {
        return service.startCameraRecording(id);
    }

    @GetMapping("/stop/{id}")
    public Camera stopCameraRecording(@PathVariable Long id) {
        return service.stopCameraRecording(id);
    }


}
