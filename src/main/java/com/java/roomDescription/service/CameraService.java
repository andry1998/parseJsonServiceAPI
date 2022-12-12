package com.java.roomDescription.service;
import java.util.List;
import java.util.Set;

public interface CameraService<T> {
    void cameraSynchronization();
    List<T> getListCamera();
    Set<String> getRooms();
    List<T> getCamerasByRoom(String room);
    List<T> getCamerasByFavorites();
    T addCameraFavorites(int id);
    T cameraRecorder(int id, boolean isRec);
    T startCameraRecording(int id);
    T stopCameraRecording(int id);

}
