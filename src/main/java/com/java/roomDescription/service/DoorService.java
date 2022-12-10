package com.java.roomDescription.service;
import static com.google.common.collect.MoreCollectors.onlyElement;

import com.java.roomDescription.client.DoorClient;
import com.java.roomDescription.model.Door;
import com.java.roomDescription.model.projections.RoomsOnly;
import com.java.roomDescription.repository.DoorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class DoorService{
    final DoorRepository repository;
    final DoorClient doorClient;

    public DoorService(DoorRepository repository, DoorClient doorClient) {
        this.repository = repository;
        this.doorClient = doorClient;
    }
    public void doorSynchronization() throws IOException {
        repository.saveAll(doorClient.getInfoDoors().getData());
    }
    public List<Door> getDoorsList() {
        return repository.findAll();
    }
    public Set<String> getRooms() throws IOException {
        return repository.findAll().stream()
                .map(door -> door.getRoom())
                .collect(Collectors.toSet());
    }

    //получить список дверей по комнате
    public List<String> getCamerasByRooms(String room) {
        return repository.getDoorsByRooms(room);
    }

    //получить список избранных дверей
    public List<String> getFavoriteDoors() {
        return repository.getFavoriteDoors();
    }

    //добавление двери в избранное (favorites)
    public void addFavoriteDoor(int id, boolean isFavorites) {
        Door door = getDoorsList().stream()
                .filter(d -> d.getId() == id)
                .peek(d -> d.setFavorites(isFavorites))
                .collect(onlyElement());
        repository.save(door);
    }

    public void deleteData(int id) {
        repository.deleteById((long) id);
    }

    public void updateData() throws IOException {
        List<Door> doorList = doorClient.getInfoDoors().getData().stream()
                .peek(door -> repository.updateDoors(
                        door.getName(),
                        door.getRoom(),
                        door.getSnapshot(),
                        door.getId()))
                .collect(Collectors.toList());
        System.out.println(getDoorsList());
        System.out.println(doorList.size());
        System.out.println("data update");
    }
}
