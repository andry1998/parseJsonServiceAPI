package com.java.roomDescription.service;

import com.java.roomDescription.client.ClientAPI;
import com.java.roomDescription.model.Camera;
import com.java.roomDescription.model.Door;
import com.java.roomDescription.model.Room;
import com.java.roomDescription.model.dto.CameraDTO;
import com.java.roomDescription.model.dto.DoorDTO;
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
    @Transactional
    public void cameraSynchronization() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CameraDTO dto= new CameraDTO();
        List<Camera> cameras = dto.convertToEntityList(client.getInfoCameras().getData().getCameras());
        cameras.stream()
                .peek(camera -> {
            if(cameraRepository.existsById(camera.getId())) {
                CriteriaQuery query = cb.createQuery();
                Root<Camera> root = query.from(Camera.class);
                query.select(root);
                query.where(cb.equal(root.get("id"), camera.getId()));
                Camera c = (Camera) em.createQuery(query).getSingleResult();
                c.setId(camera.getId());
                c.setName(camera.getName());
                c.setRoom(camera.getRoom());
                c.setSnapshot(camera.getSnapshot());
                cameraRepository.save(c);
            }
            else
                cameraRepository.save(camera);
        }).collect(Collectors.toList());
    }

    @Transactional
    public void doorSynchronization() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        client.getInfoDoors().getData().stream()
                .map(dto -> dto.convertToEntity(dto))
                .peek(door -> {
                    if(doorRepository.existsById(door.getId())) {
                        CriteriaQuery query = cb.createQuery();
                        Root<Door> root = query.from(Door.class);
                        query.select(root);
                        query.where(cb.equal(root.get("id"), door.getId()));
                        Door d = (Door) em.createQuery(query).getSingleResult();
                        //Door d = selectShortDataFromDB(door, door.getId());
                        d.setId(door.getId());
                        d.setName(door.getName());
                        d.setRoom(door.getRoom());
                        d.setSnapshot(door.getSnapshot());
                        doorRepository.save(d);
                    }
                    else
                        doorRepository.save(door);
                })
                .collect(Collectors.toList());
    }

////    public <E> E selectShortDataFromDB(E e, Long id) {
////        CriteriaBuilder cb = em.getCriteriaBuilder();
////        CriteriaQuery query = cb.createQuery();
////        Root<E> root = query.from(e.getClass());
////        query.select(root);
////        query.where(cb.equal(root.get("id"), id));
////        return (E) em.createQuery(query).getSingleResult();
////    }
////
////    public Door setShortDoorInDB(Door door, Long id, String name, Room room, String getSnapshot) {
////        door.setId(id);
////        door.setName(name);
////        door.setRoom(room);
////        door.setSnapshot(getSnapshot);
////        return door;
////    }
}
