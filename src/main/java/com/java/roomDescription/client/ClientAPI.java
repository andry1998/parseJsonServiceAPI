package com.java.roomDescription.client;

import com.java.roomDescription.controller.RetrofitController;
import com.java.roomDescription.model.Cameras;
import com.java.roomDescription.model.Doors;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ClientAPI {
    final RetrofitController retrofit;

    public ClientAPI(RetrofitController retrofit) {
        this.retrofit = retrofit;
    }
    
    public Cameras getInfoCameras() {
        try {
            return retrofit.createAPI().getInfoCameras().execute().body();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public Doors getInfoDoors() {
        try {
            return retrofit.createAPI().getInfoDoors().execute().body();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
