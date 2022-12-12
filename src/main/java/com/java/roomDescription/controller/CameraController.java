package com.java.roomDescription.controller;

import com.java.roomDescription.model.Camera;
import com.java.roomDescription.service.CameraServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cameras")
public class CameraController {
    final CameraServiceImpl service;

    public CameraController(CameraServiceImpl service) {
        this.service = service;
    }

    @GetMapping()
    public List<Camera> getListCamera() {
        return service.getListCamera();
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
    public Camera addCameraFavorites(@PathVariable int id) {
        return service.addCameraFavorites(id);
    }

    @GetMapping("/start/{id}")
    public Camera startCameraRecording(@PathVariable int id) {
        return service.startCameraRecording(id);
    }

    @GetMapping("/stop/{id}")
    public Camera stopCameraRecording(@PathVariable int id) {
        return service.stopCameraRecording(id);
    }
}
