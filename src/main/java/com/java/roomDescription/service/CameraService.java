package com.java.roomDescription.service;

import com.java.roomDescription.model.Camera;

import java.util.List;

public interface CameraService<E, ID> {
    void cameraSynchronization(List<E> e);
    List<E> getListCamera();
    //Set<String> getRooms();
    List<E> getCamerasByRoom(String room);
    List<E> getCamerasByFavorites();
    E addCameraFavorites(List<E> e, ID id);
    E cameraRecorder(List<E> e, ID id, boolean isRec);
    E startCameraRecording(List<E> e, ID id);
    E stopCameraRecording(List<E> e, ID id);
    void deleteData(ID id);

}
