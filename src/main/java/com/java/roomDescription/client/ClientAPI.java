package com.java.roomDescription.client;

import com.java.roomDescription.api.API;
import com.java.roomDescription.configuration.RetrofitConfiguration;
import com.java.roomDescription.model.dto.CamerasData;
import com.java.roomDescription.model.dto.CarsWrapper;
import com.java.roomDescription.model.dto.DoorDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Component
public class ClientAPI {

    final RetrofitConfiguration retrofit;

    final API api;

    public CarsWrapper<CamerasData> getInfoCameras() {
        try {
            return api.getInfoCameras().execute().body();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public CarsWrapper<List<DoorDTO>> getInfoDoors() {
        try {
            return api.getInfoDoors().execute().body();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
