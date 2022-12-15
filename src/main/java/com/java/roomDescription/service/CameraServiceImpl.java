package com.java.roomDescription.service;

import com.java.roomDescription.client.ClientAPI;
import com.java.roomDescription.model.Camera;
import com.java.roomDescription.model.Room;
import com.java.roomDescription.model.dto.CameraDTO;
import com.java.roomDescription.repository.CameraRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaUpdate;
import jakarta.persistence.criteria.Root;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import retrofit2.Converter;

import javax.print.attribute.standard.Destination;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.google.common.collect.MoreCollectors.onlyElement;

@Service
public class CameraServiceImpl implements CameraService<Camera, Long>{
    @PersistenceContext
    EntityManager em;
    final ModelMapper modelMapper = new ModelMapper();
    final CameraRepository repository;
    final ClientAPI client;

    public CameraServiceImpl(CameraRepository cameraRepository, ClientAPI client) {
        this.repository = cameraRepository;
        this.client = client;
    }

    @Transactional
    @Override
    public void cameraSynchronization() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        client.getInfoCameras().getData().getCameras().stream()
                .map(cameraDTO -> convertToEntity(cameraDTO))
                .peek(camera -> {
                    if(repository.existsById(camera.getId())) {
                        CriteriaUpdate<Camera> criteriaUpdate = cb.createCriteriaUpdate(Camera.class);
                        Root<Camera> root = criteriaUpdate.from(Camera.class);
                        criteriaUpdate.set("name", camera.getName())
                                .set("room", camera.getRoom())
                                .set("snapshot", camera.getSnapshot());
                        criteriaUpdate.where(cb.equal(root.get("id"), camera.getId()));
                        em.createQuery(criteriaUpdate);
                        em.createQuery(criteriaUpdate).executeUpdate();
                    }
                    else
                        repository.save(camera);
                })
                .collect(Collectors.toList());
    }

    @Transactional
    public void saveAll() {
        List<Camera> cameras = client.getInfoCameras().getData().getCameras().stream()
                .map(cameraDTO -> convertToEntity(cameraDTO))
                .collect(Collectors.toList());
        repository.saveAll(cameras);
    }

    @Override
    public List<Camera> getListCamera() {
            return repository.findAll();
    }

//    @Override
//    public Set<String> getRooms() {
//        return getListCamera().stream()
//                .map(camera -> camera.getRoom())
//                .collect(Collectors.toSet());
//    }


    /**
     * Получить список камер по комнате
     */
    @Override
    public List<Camera> getCamerasByRoom(String room) {
        return repository.getCamerasByRoom(room);
    }

    /**
     * получить список избранных камер
     */
    @Override
    public List<Camera> getCamerasByFavorites() {
        return repository.getCamerasByFavoritesIsTrue();
    }
    @Override
    public Camera addCameraFavorites(Long id) {
        Camera camera = getListCamera().stream()
                    .filter(c -> c.getId() == id)
                    .peek(d -> d.setFavorites(true))
                    .findFirst().orElseThrow();
        repository.save(camera);
        return camera;
    }
    @Override
    public Camera cameraRecorder(Long id, boolean isRec) {
        return getListCamera().stream()
                .filter(camera -> camera.getId() == id)
                .peek(d -> d.setRec(isRec))
                .findFirst().orElseThrow();
    }
    @Override
    public Camera startCameraRecording(Long id) {
        Camera camera = cameraRecorder(id, true);
        repository.save(camera);
        return camera;
    }
    @Override
    public Camera stopCameraRecording(Long id) {
        Camera camera = cameraRecorder(id, false);
        repository.save(camera);
        return camera;
    }
    @Transactional
    public void rename(Long id) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaUpdate<Camera> criteriaUpdate = cb.createCriteriaUpdate(Camera.class);
        Root<Camera> root = criteriaUpdate.from(Camera.class);
        criteriaUpdate.set("name", "Test");
        criteriaUpdate.where(cb.equal(root.get("id"), id));
        em.createQuery(criteriaUpdate).executeUpdate();
    }
    @Override
    public void deleteData(Long id) {
        repository.deleteById(id);
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    public CameraDTO convertToDTO(Camera camera) {
        modelMapper.typeMap(Camera.class, CameraDTO.class).addMappings(mapper -> {
            mapper.map(src -> src.getRoom().getName(), CameraDTO::setRoom);
        });
        return modelMapper.map(camera, CameraDTO.class);
    }

    public Camera convertToEntity(CameraDTO cameraDTO) {
        modelMapper.typeMap(CameraDTO.class, Camera.class)
                .addMappings(mapper -> mapper.<String>map(src -> src.getRoom(), (dest, v) -> dest.getRoom().setName(v)));
        return modelMapper.map(cameraDTO, Camera.class);
    }
}
