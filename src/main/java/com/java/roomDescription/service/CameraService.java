package com.java.roomDescription.service;

import com.java.roomDescription.model.Camera;
import com.java.roomDescription.model.Cameras;
import com.java.roomDescription.model.Door;
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

    public void saveAll() throws IOException {
        repository.saveAll(retrofitService.getInfoCameras().getData().getCameras());
    }

    public List<Camera> getCameras() {
        return repository.findAll();
    }

    public List<String> getRooms() throws IOException {
        return retrofitService.getInfoCameras().getData().getRoom();
    }

    //получить список камер по комнате
    public List<String> getCamerasByRooms(String room) {
        return repository.getCamerasByRooms(room);
    }

    //получить список избранных камер
    public List<String> getFavoriteCameras() {
        return repository.getFavoriteCameras();
    }

    //добавление камеры в избранное (favorites)
    public void addDoorToFavorites(String cameraName, boolean isFavorites) {
        Camera camera = getCameras().stream().filter(d -> d.getName().equals(cameraName)).peek(d -> d.setFavorites(isFavorites)).collect(onlyElement());
        repository.save(camera);
    }

    //начало и остановка записи у камеры (rec)
    public void startAndStopCameraRec(String cameraName, boolean isRec) {
        Camera camera = getCameras().stream().filter(d -> d.getName().equals(cameraName)).peek(d -> d.setRec(isRec)).collect(onlyElement());
        repository.save(camera);
    }
}
