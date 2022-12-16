package com.java.roomDescription.client;

import com.java.roomDescription.api.API;
import com.java.roomDescription.controller.RetrofitConfiguration;
import com.java.roomDescription.model.Cameras;
import com.java.roomDescription.model.Doors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;

@RequiredArgsConstructor
@Component
public class ClientAPI {
    final RetrofitConfiguration retrofit;
    final API api;

    public Cameras getInfoCameras() {
        try {
            return api.getInfoCameras().execute().body();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public Doors getInfoDoors() {
        try {
            return api.getInfoDoors().execute().body();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
