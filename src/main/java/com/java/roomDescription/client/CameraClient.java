package com.java.roomDescription.client;

import com.java.roomDescription.controller.RetrofitController;
import com.java.roomDescription.model.Cameras;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component
public class CameraClient {
    final RetrofitController retrofit;

    public CameraClient(RetrofitController retrofit) {
        this.retrofit = retrofit;
    }
    public Cameras getInfoCameras() {
        try {
            return retrofit.createAPI().getInfoCameras().execute().body();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
