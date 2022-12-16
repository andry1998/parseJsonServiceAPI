package com.java.roomDescription.service;

import com.java.roomDescription.client.ClientAPI;
import com.java.roomDescription.model.Camera;
import com.java.roomDescription.model.Door;
import com.java.roomDescription.model.Room;
import com.java.roomDescription.model.dto.CameraDTO;
import com.java.roomDescription.repository.CameraRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
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

    public List<Camera> getCamerasFromDTO() {
        return client.getInfoCameras().getData().getCameras().stream()
                .map(cameraDTO -> convertToEntity(cameraDTO))
                .collect(Collectors.toList());
    }

    public List<CameraDTO> getDTOFromCameras() {
        return repository.findAll().stream()
                .map(camera -> convertToDTO(camera))
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void cameraSynchronization() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        getCamerasFromDTO().stream()
                .peek(camera -> {
                    if(repository.existsById(camera.getId())) {
                        CriteriaQuery query = cb.createQuery();
                        Root<Camera> root = query.from(Camera.class);
                        query.select(root);
                        query.where(cb.equal(root.get("id"), camera.getId()));
                        Camera c = (Camera) em.createQuery(query).getSingleResult();
                        c.setId(camera.getId());
                        c.setName(camera.getName());
                        c.setRoom(camera.getRoom());
                        c.setSnapshot(camera.getSnapshot());
                        repository.save(c);
                    }
                    else
                        repository.save(camera);
                })
                .collect(Collectors.toList());
    }

    @Transactional
    public void saveAll() {
        repository.saveAll(getCamerasFromDTO());
    }

    @Transactional
    @Override
    public List<Camera> getListCamera() {
            return repository.findAll();
    }

    /**
     * Получить список камер по комнате
     */
    @Transactional
    @Override
    public List<Camera> getCamerasByRoom(String room) {
        return repository.getCamerasByRoom(room);
    }

    /**
     * получить список избранных камер
     */
    @Transactional
    @Override
    public List<Camera> getCamerasByFavorites() {
        return repository.getCamerasByFavoritesIsTrue();
    }
    @Transactional
    @Override
    public Camera addCameraFavorites(Long id) {
        Camera camera = getListCamera().stream()
                    .filter(c -> c.getId() == id)
                    .peek(d -> d.setFavorites(true))
                    .findFirst()
                .orElseThrow();
        repository.save(camera);
        return camera;
    }
    @Transactional
    @Override
    public Camera cameraRecorder(Long id, boolean isRec) {
        return getListCamera().stream()
                .filter(camera -> camera.getId() == id)
                .peek(d -> d.setRec(isRec))
                .findFirst()
                .orElseThrow();
    }
    @Transactional
    @Override
    public Camera startCameraRecording(Long id) {
        Camera camera = cameraRecorder(id, true);
        repository.save(camera);
        return camera;
    }
    @Transactional
    @Override
    public Camera stopCameraRecording(Long id) {
        Camera camera = cameraRecorder(id, false);
        repository.save(camera);
        return camera;
    }
    @Transactional
    @Override
    public void deleteData(Long id) {
        repository.deleteById(id);
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
