package com.java.roomDescription.service;

import java.util.List;

public interface CameraService<E, ID> {
    void cameraSynchronization(List<E> e);
    List<E> getListCamera();
    //Set<String> getRooms();
    List<E> getCamerasInRoom(String room);
    List<E> getCamerasByFavorites();
    E addCameraFavorites(List<E> e, ID id);
    E cameraRecorder(List<E> e, ID id, boolean isRec);
    E startCameraRecording(List<E> e, ID id);
    E stopCameraRecording(List<E> e, ID id);
    void deleteData(ID id);

}
