package com.java.roomDescription.service;

import com.java.roomDescription.api.CameraApi;
import com.java.roomDescription.api.DoorApi;
import com.java.roomDescription.model.Camera;
import com.java.roomDescription.model.Cameras;
import com.java.roomDescription.model.Door;
import com.java.roomDescription.model.Doors;
import org.springframework.stereotype.Service;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.List;

@Service
public class RetrofitService {
    public static final String URL_DOORS = "http://cars.cprogroup.ru/api/rubetek/doors/";
    public static final String URL_CAMERAS = "http://cars.cprogroup.ru/api/rubetek/cameras/";
    private Retrofit buildRetrofit(String url) {
        return  new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public DoorApi createRetrofitDoor(String url) {
        return buildRetrofit(url).create(DoorApi.class);
    }

    public CameraApi createRetrofitCamera(String url) {
        return buildRetrofit(url).create(CameraApi.class);
    }

    public Doors getInfoDoors() throws IOException {
        return createRetrofitDoor(URL_DOORS).getInfoDoors().execute().body();
    }

    public Cameras getInfoCameras() throws IOException {
        return createRetrofitCamera(URL_CAMERAS).getInfoCameras().execute().body();
    }
}
