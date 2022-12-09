package com.java.roomDescription.repository;

import com.java.roomDescription.model.Camera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CameraRepository extends JpaRepository<Camera, Long> {
    @Query(value = "SELECT name FROM cameras WHERE room LIKE %?1%", nativeQuery = true)
    List<String> getCamerasByRooms(String room);

    @Query(value = "SELECT name FROM cameras WHERE favorites = true", nativeQuery = true)
    List<String> getFavoriteCameras();
}
