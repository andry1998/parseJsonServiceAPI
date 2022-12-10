package com.java.roomDescription.client;

import com.java.roomDescription.model.Doors;
import com.java.roomDescription.service.RetrofitService;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class DoorClient {
    final RetrofitService retrofitService;

    public DoorClient(RetrofitService retrofitService) {
        this.retrofitService = retrofitService;
    }

    public Doors getInfoDoors() throws IOException {
        return retrofitService.createAPI().getInfoDoors().execute().body();
    }
}
