package com.java.roomDescription.service;

import com.java.roomDescription.client.ClientAPI;
import com.java.roomDescription.model.Room;
import com.java.roomDescription.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@RequiredArgsConstructor
@Service
public class RoomServiceImpl implements RoomService{

    final RoomRepository roomRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Room> getRooms() {
        return roomRepository.findAll();
    }

}
