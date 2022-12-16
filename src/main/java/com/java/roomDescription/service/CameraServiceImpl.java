package com.java.roomDescription.service;

import com.java.roomDescription.client.ClientAPI;
import com.java.roomDescription.model.Camera;
import com.java.roomDescription.model.dto.CameraDTO;
import com.java.roomDescription.repository.CameraRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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
    public void cameraSynchronization(List<Camera> cameras) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        cameras.stream()
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
    public Camera addCameraFavorites(List<Camera> cameras, Long id) {
        Camera addCamera = cameras.stream()
                    .filter(camera -> camera.getId() == id)
                    .peek(camera -> camera.setFavorites(true))
                    .findFirst()
                .orElseThrow();
        repository.save(addCamera);
        return addCamera;
    }
    @Transactional
    @Override
    public Camera cameraRecorder(List<Camera> cameras, Long id, boolean isRec) {
        return cameras.stream()
                .filter(camera -> camera.getId() == id)
                .peek(d -> d.setRec(isRec))
                .findFirst()
                .orElseThrow();
    }
    @Transactional
    @Override
    public Camera startCameraRecording(List<Camera> cameras, Long id) {
        Camera camera = cameraRecorder(cameras, id, true);
        repository.save(camera);
        return camera;
    }
    @Transactional
    @Override
    public Camera stopCameraRecording(List<Camera> cameras, Long id) {
        Camera camera = cameraRecorder(cameras, id, false);
        repository.save(camera);
        return camera;
    }
    @Transactional
    @Override
    public void deleteData(Long id) {
        repository.deleteById(id);
    }

}
