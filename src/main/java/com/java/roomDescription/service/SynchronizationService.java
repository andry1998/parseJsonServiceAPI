package com.java.roomDescription.service;

import com.java.roomDescription.client.ClientAPI;
import com.java.roomDescription.model.Camera;
import com.java.roomDescription.model.Door;
import com.java.roomDescription.model.Room;
import com.java.roomDescription.model.dto.*;
import com.java.roomDescription.repository.CameraRepository;
import com.java.roomDescription.repository.DoorRepository;
import com.java.roomDescription.repository.RoomRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Service
public class SynchronizationService {

    @PersistenceContext
    EntityManager em;

    final ClientAPI client;

    final RoomRepository roomRepository;

    final CameraRepository cameraRepository;

    final DoorRepository doorRepository;

    @Transactional
    public void dataSynchronization() {
        roomSynchronization();
        cameraSynchronization();
        doorSynchronization();
    }

    @Transactional
    public void roomSynchronization()  {

        final RoomMapper mapper = new RoomMapper();

        var doorRooms = client.getInfoDoors().getData()
                .stream()
                .map(DoorDTO::getRoom);

        var cameraRooms = client.getInfoCameras().getData()
                .getRoom()
                .stream();

        var roomsDto = Stream.concat(doorRooms, cameraRooms)
                .filter(Objects::nonNull)
                .map(RoomDTO::new)
                .collect(Collectors.toSet());

        List<Room> rooms = mapper.mapToEntityList(roomsDto.stream().toList());

        roomRepository.saveAll(rooms);
    }
    @Transactional
    public void cameraSynchronization() {
        CameraMapper mapper = new CameraMapper();
        mapper.mapToEntityList(client.getInfoCameras().getData().getCameras(), roomRepository)
                .forEach(camera -> {
                    if(cameraRepository.existsById(camera.getId())) {
                        Camera shortCamera = selectShortDataFromDB(camera, camera.getId());
                        setAndSaveShortCameraInDB(shortCamera, camera, cameraRepository);
                    }
                    else
                        cameraRepository.save(camera);
                });
    }

    @Transactional
    public void doorSynchronization() {
        DoorMapper mapper = new DoorMapper();
        mapper.mapToEntityList(client.getInfoDoors().getData(), roomRepository)
                .forEach(door -> {
                    if(doorRepository.existsById(door.getId())) {
                        Door shortDoor = selectShortDataFromDB(door, door.getId());
                        setAndSaveShortDoorInDB(shortDoor, door, doorRepository);
                    }
                    else
                        doorRepository.save(door);
                });
    }

    public <E> E selectShortDataFromDB(E entity, Long id) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery query = cb.createQuery();
        Root<E> root = query.from(entity.getClass());
        query.select(root);
        query.where(cb.equal(root.get("id"), id));
        return (E) em.createQuery(query).getSingleResult();
    }

    public void setAndSaveShortDoorInDB(Door doorShort, Door door, DoorRepository doorRepository) {
        doorShort.setId(door.getId());
        doorShort.setName(door.getName());
        doorShort.setRoom(door.getRoom());
        doorShort.setSnapshot(door.getSnapshot());
        doorRepository.save(doorShort);
    }

    public void setAndSaveShortCameraInDB(Camera cameraShort, Camera camera, CameraRepository cameraRepository) {
        cameraShort.setId(camera.getId());
        cameraShort.setName(camera.getName());
        cameraShort.setRoom(camera.getRoom());
        cameraShort.setSnapshot(camera.getSnapshot());
        cameraRepository.save(cameraShort);
    }
}
