package com.java.roomDescription;

import com.java.roomDescription.controller.RetrofitController;
import com.java.roomDescription.service.CameraServiceImpl;
import com.java.roomDescription.service.DoorServiceImpl;
import com.java.roomDescription.service.RoomServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
@Slf4j
@Component
public class DataSynchronizationTask {
    final CameraServiceImpl cameraService;
    final DoorServiceImpl doorService;
    final RoomServiceImpl roomService;

    public DataSynchronizationTask(CameraServiceImpl cameraService, DoorServiceImpl doorService, RoomServiceImpl roomService) {
        this.cameraService = cameraService;
        this.doorService = doorService;
        this.roomService = roomService;
    }


    @Scheduled(fixedRateString = "${task.time}")
    public void loadingAndUnloadingData() throws IOException {
//        cameraService.cameraSynchronization();
//        doorService.doorSynchronization();
//        roomService.roomSynchronization();
        log.info("loading and unloading success");
    }
}
