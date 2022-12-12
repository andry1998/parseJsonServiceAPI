package com.java.roomDescription.client;

import com.java.roomDescription.controller.RetrofitController;
import com.java.roomDescription.model.Doors;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class DoorClient {
    final RetrofitController retrofit;

    public DoorClient(RetrofitController retrofit) {
        this.retrofit = retrofit;
    }

    public Doors getInfoDoors() {
        try {
            return retrofit.createAPI().getInfoDoors().execute().body();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
