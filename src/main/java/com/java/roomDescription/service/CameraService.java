package com.java.roomDescription.service;

import com.java.roomDescription.model.Camera;
import com.java.roomDescription.repository.CameraRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

import static com.google.common.collect.MoreCollectors.onlyElement;

@Service
public class CameraService {
    public static final String URL_CAMERAS = "http://cars.cprogroup.ru/api/rubetek/cameras/";

    private final CameraRepository repository;
    private final RetrofitService retrofitService;

    public CameraService(CameraRepository cameraRepository, RetrofitService retrofitService) {
        this.repository = cameraRepository;
        this.retrofitService = retrofitService;
    }

    public void saveAllCameras() throws IOException {
        repository.saveAll(retrofitService.getInfoCameras().getData().getCameras());
    }

    public List<Camera> getCamerasList() {
        return repository.findAll();
    }

    public List<String> getRoomCameras() throws IOException {
        return retrofitService.getInfoCameras().getData().getRoom();
    }

    /**
     * Получить список камер по комнате
     */
    public List<String> getCamerasByRooms(String room) {
        return repository.getCamerasByRooms(room);
    }

    //получить список избранных камер
    public List<String> getFavoriteCameras() {
        return repository.getFavoriteCameras();
    }

    //добавление камеры в избранное (favorites)
    public void addFavoriteCamera(int id, boolean isFavorites) {
        Camera camera = getCamerasList().stream()
                .filter(d -> d.getId() == id)
                .peek(d -> d.setFavorites(isFavorites))
                .findFirst().orElseThrow();
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
