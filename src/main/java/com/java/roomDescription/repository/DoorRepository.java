package com.java.roomDescription.repository;

import com.java.roomDescription.model.Door;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface DoorRepository extends JpaRepository<Door, Long> {
    @Query(value = "SELECT room FROM doors", nativeQuery = true)
    List<String> getRoomDoors();

    @Query(value = "SELECT name FROM doors WHERE room LIKE %?1%", nativeQuery = true)
    List<String> getDoorsByRooms(String room);

    @Query(value = "SELECT name FROM doors WHERE favorites = true", nativeQuery = true)
    List<String> getFavoriteDoors();

    @Transactional
    @Modifying
    @Query(value = "update doors set name = ?1, room = ?2, snapshot = ?3 WHERE id = ?4", nativeQuery = true)
    void updateDoors(String name, String room, String snapshot, int id);
}
