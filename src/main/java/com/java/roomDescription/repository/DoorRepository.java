package com.java.roomDescription.repository;

import com.java.roomDescription.model.Door;
import com.java.roomDescription.model.projections.RoomsOnly;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface DoorRepository extends JpaRepository<Door, Long> {
    List<Door> getDoorsByRoom(String room);
    List<Door> getDoorsByFavoritesIsTrue();

    @Transactional
    @Modifying
    @Query(value = "update doors set name = ?1, room = ?2, snapshot = ?3 WHERE id = ?4", nativeQuery = true)
    void updateDoors(String name, String room, String snapshot, int id);
}
