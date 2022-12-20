package com.java.roomDescription.service;

import java.util.List;

public interface CameraService<Camera, Long> {

    List<Camera> getListCamera();

    List<Camera> getCamerasInRoom(String room);

    List<Camera> getCamerasByFavorites();

    Camera addCameraFavorites(Long id);

    Camera cameraRecorder(Long id, boolean isRec);

    Camera startCameraRecording(Long id);

    Camera stopCameraRecording(Long id);

    void deleteData(Long id);

}
