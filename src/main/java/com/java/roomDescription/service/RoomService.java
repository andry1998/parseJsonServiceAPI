package com.java.roomDescription.service;

import java.util.List;
import java.util.Set;

public interface RoomService<T> {
    void roomSynchronization();
    List<T> getListRoom();
    void deleteByName(String name);
}
