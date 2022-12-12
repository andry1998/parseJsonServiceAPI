package com.java.roomDescription.service;

import com.java.roomDescription.client.CameraClient;
import com.java.roomDescription.model.Camera;
import com.java.roomDescription.repository.CameraRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.google.common.collect.MoreCollectors.onlyElement;

@Service
public class CameraServiceImpl implements CameraService{
    final CameraRepository repository;
    final CameraClient cameraClient;

    public CameraServiceImpl(CameraRepository cameraRepository, CameraClient cameraClient) {
        this.repository = cameraRepository;
        this.cameraClient = cameraClient;
    }

    @Override
    public void cameraSynchronization() {
        repository.saveAll(cameraClient.getInfoCameras().getData().getCameras());
    }

    @Override
    public List<Camera> getListCamera() {
            return repository.findAll();
    }

    @Override
    public Set<String> getRooms() {
        return getListCamera().stream()
                .map(camera -> camera.getRoom())
                .collect(Collectors.toSet());
    }


    /**
     * Получить список камер по комнате
     */
    @Override
    public List<Camera> getCamerasByRoom(String room) {
        return repository.getCamerasByRoom(room);
    }

    /**
     * получить список избранных камер
     */
    @Override
    public List<Camera> getCamerasByFavorites() {
        return repository.getCamerasByFavoritesIsTrue();
    }
    @Override
    public Camera addCameraFavorites(int id) {
        Camera camera = null;
        try {
            camera = getListCamera().stream()
                    .filter(d -> d.getId() == id)
                    .peek(d -> d.setFavorites(true))
                    .findFirst().orElseThrow(() -> new Exception());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        repository.save(camera);
        return camera;
    }
    @Override
    public Camera cameraRecorder(int id, boolean isRec) {
        Camera camera = getListCamera().stream()
                .filter(d -> d.getId() == id)
                .peek(d -> d.setRec(isRec))
                .collect(onlyElement());
        return camera;
    }
    @Override
    public Camera startCameraRecording(int id) {
        Camera camera = cameraRecorder(id, true);
        repository.save(camera);
        return camera;
    }
    @Override
    public Camera stopCameraRecording(int id) {
        Camera camera = cameraRecorder(id, false);
        repository.save(camera);
        return camera;
    }
}
