package com.java.roomDescription.service;

import com.java.roomDescription.model.Door;

import java.util.List;
import java.util.Set;

public interface DoorService<T> {
    void doorSynchronization();
    List<T> getListDoor();
    Set<String> getRooms();
    List<T> getDoorsByRoom(String room);
    List<T> getDoorsByFavorites();
    T addDoorFavorites(int id);
    void deleteData(int id);
    void updateData();
}
