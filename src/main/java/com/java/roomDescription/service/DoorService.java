package com.java.roomDescription.service;
import static com.google.common.collect.MoreCollectors.onlyElement;

import com.java.roomDescription.model.Door;
import com.java.roomDescription.repository.DoorRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class DoorService{
    public static final String URL_DOORS = "http://cars.cprogroup.ru/api/rubetek/doors/";

    private final DoorRepository repository;
    private final RetrofitService retrofitService;

    public DoorService(DoorRepository repository, RetrofitService retrofitService) {
        this.repository = repository;
        this.retrofitService = retrofitService;
    }
    public void saveAll() throws IOException {
        repository.saveAll(retrofitService.getInfoDoors().getData());
    }
    public List<Door> getDoors() {
        return repository.findAll();
    }
    public List<String> getRooms() throws IOException {
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
    public void addDoorToFavorites(String doorName, boolean isFavorites) {
        Door door = getDoors().stream().filter(d -> d.getName().equals(doorName)).peek(d -> d.setFavorites(isFavorites)).collect(onlyElement());
        repository.save(door);
    }

    public void deleteData(int id) {
        repository.deleteById((long) id);
    }

    public Map<Integer, String> getIdAndFavValue() {
        return repository.getIdAndFavoriteValue();
    }

    public void unloadingData() throws IOException {
        //List<Integer> idRepository = repository.findAll().stream().map(i -> i.getId()).collect(Collectors.toList());
        //return retrofitService.getInfoDoors().getData().stream().filter(fav -> fav.isFavorites()).collect(Collectors.toList());
    }
}
