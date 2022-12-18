package com.java.roomDescription;

import com.java.roomDescription.client.ClientAPI;
import com.java.roomDescription.controller.CameraController;
import com.java.roomDescription.controller.DoorController;
import com.java.roomDescription.service.CameraServiceImpl;
import com.java.roomDescription.service.DoorServiceImpl;
import com.java.roomDescription.service.RoomServiceImpl;
import com.java.roomDescription.service.SynchronizationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Slf4j
@Component
public class DataSynchronizationTask {
    final CameraServiceImpl cameraService;
    final DoorServiceImpl doorService;
    final RoomServiceImpl roomService;
    final CameraController cameraController;
    final DoorController doorController;
    final ClientAPI clientAPI;
    final SynchronizationService synchronizationService;
    @Scheduled(fixedRateString = "${task.time}")
    public void loadingAndUnloadingData() {
//        roomService.roomSynchronization();
//        cameraService.cameraSynchronization(cameraController.convertToEntityList(clientAPI.getInfoCameras().getData().getCameras()));
//        doorService.doorSynchronization(doorController.convertToEntityList(clientAPI.getInfoDoors().getData()));
        synchronizationService.dataSynchronization();
        log.info("loading and unloading success");
    }
}
