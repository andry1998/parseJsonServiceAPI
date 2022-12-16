package com.java.roomDescription.service;

import com.java.roomDescription.model.Door;

import java.util.List;
import java.util.Set;

public interface DoorService<E, ID> {
    void doorSynchronization();
    List<E> getListDoor();
    List<E> getDoorsByRoom(String room);
    List<E> getDoorsByFavorites();
    E addDoorFavorites(ID id);
    void deleteData(ID id);
}
