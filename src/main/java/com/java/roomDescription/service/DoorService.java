package com.java.roomDescription.service;

import com.java.roomDescription.model.Door;

import java.util.List;

public interface DoorService<E, ID> {
    void doorSynchronization(List<E> e);
    List<E> getListDoor();
    List<E> getDoorsByRoom(String room);
    List<E> getDoorsByFavorites();
    E addDoorFavorites(List<E> e, ID id);
    void deleteData(ID id);
}
