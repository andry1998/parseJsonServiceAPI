package com.java.roomDescription.api;

import com.java.roomDescription.model.dto.CamerasData;
import com.java.roomDescription.model.dto.CarsWrapper;
import com.java.roomDescription.model.dto.DoorDTO;
import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;

public interface API {
    @GET("doors/")
    Call<CarsWrapper<List<DoorDTO>>> getInfoDoors();

    @GET("cameras/")
    Call<CarsWrapper<CamerasData>> getInfoCameras();
}
