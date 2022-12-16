package com.java.roomDescription.service;

import com.java.roomDescription.client.ClientAPI;
import com.java.roomDescription.model.Camera;
import com.java.roomDescription.model.Door;
import com.java.roomDescription.model.Room;
import com.java.roomDescription.model.dto.DoorDTO;
import com.java.roomDescription.repository.RoomRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class RoomServiceImpl implements RoomService{
    final ClientAPI client;
    final RoomRepository roomRepository;

    public RoomServiceImpl(ClientAPI client, RoomRepository roomRepository) {
        this.client = client;
        this.roomRepository = roomRepository;
    }


    @Transactional
    @Override
    public void roomSynchronization()  {

        var doorRooms = client.getInfoDoors().getData()
                .stream()
                .map(DoorDTO::getRoom);

        var cameraRooms = client.getInfoCameras().getData()
                .getRoom()
                .stream();

        var rooms = Stream.concat(doorRooms, cameraRooms)
                .filter(Objects::nonNull)
                .map(Room::new)
                .collect(Collectors.toSet());

        roomRepository.saveAll(rooms);
    }

    @Override
    public List getListRoom() {
        return roomRepository.findAll();
    }

}
