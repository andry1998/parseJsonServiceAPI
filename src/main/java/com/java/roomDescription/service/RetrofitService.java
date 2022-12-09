package com.java.roomDescription.service;

import com.java.roomDescription.api.API;
import com.java.roomDescription.api.CameraApi;
import com.java.roomDescription.api.DoorApi;
import com.java.roomDescription.model.Cameras;
import com.java.roomDescription.model.Doors;
import org.springframework.stereotype.Service;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

@Service
public class RetrofitService {

    public static final String HOST = "http://cars.cprogroup.ru/api/rubetek/";
    public static final String URL_DOORS = "http://cars.cprogroup.ru/api/rubetek/doors/";
    public static final String URL_CAMERAS = "http://cars.cprogroup.ru/api/rubetek/cameras/";

    Retrofit retrofit = null;
    private Retrofit getRetrofit() {
        if(retrofit == null)
        retrofit = new Retrofit.Builder()
                .baseUrl(HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }

    public API createRetrofitDoor() {
        return getRetrofit().create(API.class);
    }

    public Doors getInfoDoors() throws IOException {
        return createRetrofitDoor().getInfoDoors().execute().body();
    }

    public Cameras getInfoCameras() throws IOException {
        return createRetrofitCamera(URL_CAMERAS).getInfoCameras().execute().body();
    }
}
