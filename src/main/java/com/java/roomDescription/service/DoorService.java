package com.java.roomDescription.service;
import static com.google.common.collect.MoreCollectors.onlyElement;

import com.java.roomDescription.model.Door;
import com.java.roomDescription.repository.DoorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
public class DoorService{
    public static final String URL_DOORS = "http://cars.cprogroup.ru/api/rubetek/doors/";

    private final DoorRepository repository;
    private final RetrofitService retrofitService;

    public DoorService(DoorRepository repository, RetrofitService retrofitService) {
        this.repository = repository;
        this.retrofitService = retrofitService;
    }
    public void saveAllDoors() throws IOException {
        repository.saveAll(retrofitService.getInfoDoors().getData());
    }
    public List<Door> getDoorsList() {
        return repository.findAll();
    }
    public List<String> getRoomDoors() throws IOException {
        return repository.getRoomDoors();
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
        List<Door> doorList = retrofitService.getInfoDoors().getData().stream()
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
