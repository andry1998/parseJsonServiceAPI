package com.java.roomDescription.api;

import com.java.roomDescription.model.Cameras;
import retrofit2.Call;
import retrofit2.http.GET;

public interface CameraApi {
    @GET(".")
    Call<Cameras> getInfoCameras();
}
