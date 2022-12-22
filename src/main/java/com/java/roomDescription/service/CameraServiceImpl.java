package com.java.roomDescription.service;

import com.java.roomDescription.client.ClientAPI;
import com.java.roomDescription.model.Camera;
import com.java.roomDescription.repository.CameraRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CameraServiceImpl implements CameraService<Camera, Long>{

    final CameraRepository repository;

    @Transactional(readOnly = true)
    @Override
    public List<Camera> getListCamera() {
            return repository.findAll();
    }

    /**
     * Получить список камер по комнате
     */

    @Transactional(readOnly = true)
    @Override
    public List<Camera> getCamerasInRoom(String room) {
        return repository.getCameraByRoomName(room);
    }

    /**
     * получить список избранных камер
     */

    @Transactional(readOnly = true)
    @Override
    public List<Camera> getCamerasByFavorites() {
        return repository.getCamerasByFavoritesIsTrue();
    }

    @Transactional
    @Override
    public Camera addCameraFavorites(Long id) {
        Camera camera = repository.getCameraById(id);
        camera.setFavorites(true);
        return repository.save(camera);
    }

    @Transactional(readOnly = true)
    @Override
    public Camera cameraRecorder(Long id, boolean isRec) {
        Camera camera = repository.getCameraById(id);
        camera.setRec(isRec);
        return camera;
    }

    @Transactional
    @Override
    public Camera startCameraRecording(Long id) {
        Camera camera = cameraRecorder(id, true);
        repository.save(camera);
        return camera;
    }

    @Transactional
    @Override
    public Camera stopCameraRecording(Long id) {
        Camera camera = cameraRecorder(id, false);
        repository.save(camera);
        return camera;
    }

    @Transactional
    @Override
    public void deleteData(Long id) {
        repository.deleteById(id);
    }

}
