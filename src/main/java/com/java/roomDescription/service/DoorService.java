package com.java.roomDescription.service;

import java.util.List;

public interface DoorService<Door, Long> {
    List<Door> getListDoor();
    List<Door> getDoorsByRoom(String room);
    List<Door> getDoorsByFavorites();
    Door addDoorFavorites(Long id);
    void deleteData(Long id);
}
