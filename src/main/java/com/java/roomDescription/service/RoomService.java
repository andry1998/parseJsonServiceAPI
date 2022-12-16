package com.java.roomDescription.service;

import java.util.List;

public interface RoomService<E> {
    void roomSynchronization();
    List<E> getListRoom();
}
