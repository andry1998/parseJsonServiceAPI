package com.java.roomDescription.service;
import static com.google.common.collect.MoreCollectors.onlyElement;

import com.java.roomDescription.client.ClientAPI;
import com.java.roomDescription.model.Door;
import com.java.roomDescription.model.dto.DoorDTO;
import com.java.roomDescription.repository.DoorRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class DoorServiceImpl implements DoorService<Door, Long>{
    @PersistenceContext
    EntityManager em;
    final ModelMapper modelMapper = new ModelMapper();
    final DoorRepository repository;
    final ClientAPI client;

    public DoorServiceImpl(DoorRepository repository, ClientAPI client) {
        this.repository = repository;
        this.client = client;
    }
    @Transactional
    @Override
    public void doorSynchronization() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        client.getInfoDoors().getData().stream()
                .map(door -> convertToEntity(door))
                .peek((door) -> {
                    if(repository.existsById(door.getId())) {
                        CriteriaQuery query = cb.createQuery();
                        Root<Door> root = query.from(Door.class);
                        query.select(root);
                        query.where(cb.equal(root.get("id"), door.getId()));
                        Door d = (Door) em.createQuery(query).getSingleResult();
                        d.setId(door.getId());
                        d.setName(door.getName());
                        d.setRoom(door.getRoom());
                        d.setSnapshot(door.getSnapshot());
                        repository.save(d);
                    }
                    else
                        repository.save(door);
                })
                .collect(Collectors.toList());
    }
    @Transactional
    @Override
    public List<Door> getListDoor() {
        return repository.findAll();
    }
    @Transactional
    @Override
    public List<Door> getDoorsByRoom(String room) {
        return repository.getDoorsByRoom(room);
    }
    @Transactional
    @Override
    public List<Door> getDoorsByFavorites() {
        return repository.getDoorsByFavoritesIsTrue();
    }
    @Transactional
    @Override
    public Door addDoorFavorites(Long id) {
        Door door = getListDoor().stream()
                .filter(d -> d.getId() == id)
                .peek(d -> d.setFavorites(true))
                .findFirst()
                .orElseThrow();
        repository.save(door);
        return door;
    }
    @Transactional
    @Override
    public void deleteData(Long id) {
        repository.deleteById(id);
    }

    public DoorDTO convertToDTO(Door door) {
        modelMapper.typeMap(Door.class, DoorDTO.class).addMappings(mapper -> {
            mapper.map(src -> src.getRoom().getName(), DoorDTO::setRoom);
        });
        return modelMapper.map(door, DoorDTO.class);
    }

    public Door convertToEntity(DoorDTO doorDTO) {
        modelMapper.typeMap(DoorDTO.class, Door.class)
                .addMappings(mapper -> mapper.<String>map(src -> src.getRoom(), (dest, v) -> dest.getRoom().setName(v)));
        return modelMapper.map(doorDTO, Door.class);
    }
}
