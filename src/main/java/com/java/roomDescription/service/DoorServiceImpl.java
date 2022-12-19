package com.java.roomDescription.service;

import com.java.roomDescription.client.ClientAPI;
import com.java.roomDescription.model.Door;
import com.java.roomDescription.repository.DoorRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class DoorServiceImpl implements DoorService<Door, Long>{

    final DoorRepository repository;

    final ClientAPI client;

    @Transactional(readOnly = true)
    @Override
    public List<Door> getListDoor() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Door> getDoorsByRoom(String room) {
        return repository.getDoorsByRoom(room);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Door> getDoorsByFavorites() {
        return repository.getDoorsByFavoritesIsTrue();
    }

    @Transactional
    @Override
    public Door addDoorFavorites(Long id) {
        Door door = repository.getDoorById(id);
        door.setFavorites(true);
        repository.save(door);
        return door;
    }

    @Transactional
    @Override
    public void deleteData(Long id) {
        repository.deleteById(id);
    }
}
