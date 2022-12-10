package com.java.roomDescription.service;

import com.java.roomDescription.client.CameraClient;
import com.java.roomDescription.client.DoorClient;
import com.java.roomDescription.model.Camera;
import com.java.roomDescription.repository.CameraRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

import static com.google.common.collect.MoreCollectors.onlyElement;

@Service
public class CameraService {
    public static final String URL_CAMERAS = "http://cars.cprogroup.ru/api/rubetek/cameras/";

    final CameraRepository repository;
    final CameraClient cameraClient;

    public CameraService(CameraRepository cameraRepository, CameraClient cameraClient) {
        this.repository = cameraRepository;
        this.cameraClient = cameraClient;
    }

    public void cameraSynchronization() throws IOException {
        repository.saveAll(cameraClient.getInfoCameras().getData().getCameras());
    }

    public List<Camera> getCamerasList() {
        return repository.findAll();
    }

    public List<String> getRoomCameras() throws IOException {
        return cameraClient.getInfoCameras().getData().getRoom();
    }

    /**
     * Получить список камер по комнате
     */
    public List<Camera> getCamerasByRooms(String room) {
        return repository.getCamerasByRoom(room);
    }

    /**
     * получить список избранных камер
     */
    public List<Camera> getCamerasByFavorites() {
        return repository.getCamerasByFavoritesIsTrue();
    }

    //добавление камеры в избранное (favorites)
    public void addFavoriteCamera(int id, boolean isFavorites) throws Exception {
        Camera camera = getCamerasList().stream()
                .filter(d -> d.getId() == id)
                .peek(d -> d.setFavorites(isFavorites))
                .findFirst().orElseThrow(() -> new Exception("Camera not found"));
        repository.save(camera);
    }

    //начало записи у камеры (rec)
    public void startCameraRecorder(int id) {
        Camera camera = getCamerasList().stream()
                .filter(d -> d.getId() == id)
                .peek(d -> d.setRec(true))
                .collect(onlyElement());
        repository.save(camera);
    }

    ////остановка записи у камеры (rec)
    public void stopCameraRecorder(int id) {
        Camera camera = getCamerasList().stream()
                .filter(d -> d.getId() == id)
                .peek(d -> d.setRec(false))
                .collect(onlyElement());
        repository.save(camera);
    }
}
