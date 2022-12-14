package com.java.roomDescription.service;
import java.util.List;
import java.util.Set;

public interface CameraService<E, ID> {
    void cameraSynchronization();
    List<E> getListCamera();
    Set<String> getRooms();
    List<E> getCamerasByRoom(String room);
    List<E> getCamerasByFavorites();
    E addCameraFavorites(ID id);
    E cameraRecorder(ID id, boolean isRec);
    E startCameraRecording(ID id);
    E stopCameraRecording(ID id);
    void deleteData(ID id);

}
