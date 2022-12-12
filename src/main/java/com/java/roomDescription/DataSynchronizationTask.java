package com.java.roomDescription;

import com.java.roomDescription.controller.RetrofitController;
import com.java.roomDescription.service.CameraServiceImpl;
import com.java.roomDescription.service.DoorServiceImpl;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class DataSynchronizationTask {

    private final RetrofitController retrofit;
    private final CameraServiceImpl cameraServiceImpl;
    private final DoorServiceImpl doorServiceImpl;

    public DataSynchronizationTask(RetrofitController retrofit, CameraServiceImpl cameraServiceImpl, DoorServiceImpl doorServiceImpl) {
        this.retrofit = retrofit;
        this.cameraServiceImpl = cameraServiceImpl;
        this.doorServiceImpl = doorServiceImpl;
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
