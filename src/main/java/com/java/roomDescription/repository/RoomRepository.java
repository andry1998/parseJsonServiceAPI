package com.java.roomDescription.repository;

import com.java.roomDescription.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    Room getRoomByName(String name);
}
