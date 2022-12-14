package com.java.roomDescription.service;

import com.java.roomDescription.client.ClientAPI;
import com.java.roomDescription.model.Room;
import com.java.roomDescription.repository.RoomRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RoomServiceImpl implements RoomService{
    final ClientAPI client;
    final RoomRepository roomRepository;

    public RoomServiceImpl(ClientAPI client, RoomRepository roomRepository) {
        this.client = client;
        this.roomRepository = roomRepository;
    }

    @Override
    public void roomSynchronization()  {
        Set<Room> rooms = new HashSet<>();
        rooms.addAll(client.getInfoDoors().getData().stream()
                .map(door -> new Room(door.getRoom())).collect(Collectors.toSet()));
        rooms.addAll(client.getInfoCameras().getData().getRoom().stream()
                .map(room -> new Room(room))
                .collect(Collectors.toSet()));
        rooms.remove(new Room(null));
        roomRepository.saveAll(rooms);
    }

    @Override
    public List getListRoom() {
        return roomRepository.findAll();
    }

    @Transactional
    @Override
    public void deleteByName(String name) {
        roomRepository.deleteByName(name);
    }

}
