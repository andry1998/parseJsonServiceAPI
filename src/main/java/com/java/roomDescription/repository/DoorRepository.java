package com.java.roomDescription.repository;

import com.java.roomDescription.model.Door;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

public interface DoorRepository extends JpaRepository<Door, Long> {
    @Query(value = "SELECT room FROM doors", nativeQuery = true)
    List<String> getRoomDoors();

    @Query(value = "SELECT name FROM doors WHERE room LIKE %?1%", nativeQuery = true)
    List<String> getDoorsByRooms(String room);

    @Query(value = "SELECT name FROM doors WHERE favorites = true", nativeQuery = true)
    List<String> getFavoriteDoors();

    @Query(value = "SELECT id, favorite FROM doors", nativeQuery = true)
    Map<Integer, String> getIdAndFavoriteValue();
}
