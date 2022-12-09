package com.java.roomDescription;

import com.java.roomDescription.repository.DoorRepository;
import com.java.roomDescription.service.CameraService;
import com.java.roomDescription.service.DoorService;
import com.java.roomDescription.service.RetrofitService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class DataSynchronizationTask {

    private final RetrofitService retrofitService;
    private final CameraService cameraService;
    private final DoorService doorService;

    public DataSynchronizationTask(RetrofitService retrofitService, CameraService cameraService, DoorService doorService) {
        this.retrofitService = retrofitService;
        this.cameraService = cameraService;
        this.doorService = doorService;
    }

    @Scheduled(fixedRate = 1000000)
    public void loadingAndUnloadingData() throws IOException {
//        retrofitService.getInfoCameras();
//        retrofitService.getInfoDoors();
//        cameraService.saveAll();
//        doorService.saveAll();
        System.out.println("loading and unloading success");
    }
}
