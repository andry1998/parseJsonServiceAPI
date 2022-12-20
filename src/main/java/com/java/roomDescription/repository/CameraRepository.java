package com.java.roomDescription.repository;

import com.java.roomDescription.model.Camera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CameraRepository extends JpaRepository<Camera, Long> {

    /**
     * Получить список камер по комнате
     */
    List<Camera> getCamerasByRoom(String room);

    /**
     * Получить список избранных камер
     */
    List<Camera> getCamerasByFavoritesIsTrue();

    Camera getCameraById(Long id);

}
