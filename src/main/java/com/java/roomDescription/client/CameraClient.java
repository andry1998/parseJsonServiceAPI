package com.java.roomDescription.client;

import com.java.roomDescription.model.Cameras;
import com.java.roomDescription.service.RetrofitService;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component
public class CameraClient {
    final RetrofitService retrofitService;

    public CameraClient(RetrofitService retrofitService) {
        this.retrofitService = retrofitService;
    }
    public Cameras getInfoCameras() throws IOException {
        return retrofitService.createAPI().getInfoCameras().execute().body();
    }
}
