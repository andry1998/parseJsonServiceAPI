package com.java.roomDescription.service;
import static com.google.common.collect.MoreCollectors.onlyElement;

import com.java.roomDescription.client.DoorClient;
import com.java.roomDescription.model.Door;
import com.java.roomDescription.repository.CameraRepository;
import com.java.roomDescription.repository.DoorRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
public class DoorServiceImpl implements DoorService{
    private final CameraRepository cameraRepository;
    @PersistenceContext
    EntityManager em;
    final DoorRepository repository;
    final DoorClient doorClient;

    public DoorServiceImpl(DoorRepository repository, DoorClient doorClient,
                           CameraRepository cameraRepository) {
        this.repository = repository;
        this.doorClient = doorClient;
        this.cameraRepository = cameraRepository;
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
    public Door addDoorFavorites(Long id) {
        Door door = getListDoor().stream()
                .filter(d -> d.getId() == id)
                .peek(d -> d.setFavorites(true))
                .collect(onlyElement());
        repository.save(door);
        return door;
    }
    @Override
    public void deleteData(Long id) {
        repository.deleteById(id);
    }

    @Transactional
    public void rename(int id) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaUpdate<Door> criteriaUpdate = cb.createCriteriaUpdate(Door.class);
        Root<Door> root = criteriaUpdate.from(Door.class);
        criteriaUpdate.set("name", "Test");
        criteriaUpdate.where(cb.equal(root.get("id"), id));
        em.createQuery(criteriaUpdate).executeUpdate();
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
    @Transactional
    public void updateDataWithoutFavorites() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        List<Door> doorList = doorClient.getInfoDoors().getData().stream()
                .peek((door) -> {
                    if(repository.existsById(door.getId())) {
                        CriteriaUpdate<Door> criteriaUpdate = cb.createCriteriaUpdate(Door.class);
                        Root<Door> root = criteriaUpdate.from(Door.class);
                        criteriaUpdate.set("name", door.getName())
                                .set("room", door.getRoom())
                                .set("snapshot", door.getSnapshot());
                        criteriaUpdate.where(cb.equal(root.get("id"), door.getId()));
                        em.createQuery(criteriaUpdate);
                        em.createQuery(criteriaUpdate).executeUpdate();
                    }
                    else
                        repository.save(door);
                })
                .collect(Collectors.toList());
    }
}
