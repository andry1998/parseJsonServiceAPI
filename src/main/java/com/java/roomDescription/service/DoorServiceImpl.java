package com.java.roomDescription.service;
import static com.google.common.collect.MoreCollectors.onlyElement;

import com.java.roomDescription.client.DoorClient;
import com.java.roomDescription.model.Door;
import com.java.roomDescription.repository.DoorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
public class DoorServiceImpl implements DoorService{
    final DoorRepository repository;
    final DoorClient doorClient;

    public DoorServiceImpl(DoorRepository repository, DoorClient doorClient) {
        this.repository = repository;
        this.doorClient = doorClient;
    }
    @Override
    public void doorSynchronization() {
        repository.saveAll(doorClient.getInfoDoors().getData());
    }
    @Override
    public List<Door> getListDoor() {
        return repository.findAll();
    }
    @Override
    public Set<String> getRooms() {
        return repository.findAll().stream()
                .map(door -> door.getRoom())
                .collect(Collectors.toSet());
    }
    @Override
    public List<Door> getDoorsByRoom(String room) {
        return repository.getDoorsByRoom(room);
    }
    @Override
    public List<Door> getDoorsByFavorites() {
        return repository.getDoorsByFavoritesIsTrue();
    }
    @Override
    public Door addDoorFavorites(int id) {
        Door door = getListDoor().stream()
                .filter(d -> d.getId() == id)
                .peek(d -> d.setFavorites(true))
                .collect(onlyElement());
        repository.save(door);
        return door;
    }
    @Override
    public void deleteData(int id) {
        repository.deleteById((long) id);
    }
    @Override
    public void updateData() {
        List<Door> dataDB = repository.findAll();
        List<Door> doorList = doorClient.getInfoDoors().getData().stream()
                .peek(door -> repository.updateDoors(
                        door.getName(),
                        door.getRoom(),
                        door.getSnapshot(),
                        door.getId()))
                .collect(Collectors.toList());
        List<Door> newDataDoor = Stream.concat(doorList.stream(), dataDB.stream())
                .distinct()
                .filter(x -> !dataDB.contains(x) || !doorList.contains(x))
                .toList();
        repository.saveAll(newDataDoor);
    }
}
