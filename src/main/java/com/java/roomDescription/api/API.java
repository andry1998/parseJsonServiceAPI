package com.java.roomDescription.api;

import com.java.roomDescription.model.Cameras;
import com.java.roomDescription.model.Doors;
import retrofit2.Call;
import retrofit2.http.GET;

public interface API {
    @GET("doors")
    Call<Doors> getInfoDoors();

    @GET("cameras")
    Call<Cameras> getInfoCameras();
}
