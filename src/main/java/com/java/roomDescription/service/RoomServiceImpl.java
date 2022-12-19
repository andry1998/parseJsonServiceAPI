package com.java.roomDescription.service;

import com.java.roomDescription.client.ClientAPI;
import com.java.roomDescription.model.Room;
import com.java.roomDescription.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@RequiredArgsConstructor
@Service
public class RoomServiceImpl implements RoomService{

    final ClientAPI client;

    final RoomRepository roomRepository;


    @Override
    public List<Room> getNameRooms() {
        return roomRepository.findAll();
    }

}
