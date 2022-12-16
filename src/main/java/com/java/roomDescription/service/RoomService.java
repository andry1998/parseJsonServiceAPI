package com.java.roomDescription.service;

import java.util.List;
import java.util.Set;

public interface RoomService<E> {
    void roomSynchronization();
    List<E> getListRoom();
}
