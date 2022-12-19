package com.java.roomDescription.repository;

import com.java.roomDescription.model.Door;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface DoorRepository extends JpaRepository<Door, Long> {

    List<Door> getDoorsByRoom(String room);

    Door getDoorById(Long id);

    List<Door> getDoorsByFavoritesIsTrue();
}
