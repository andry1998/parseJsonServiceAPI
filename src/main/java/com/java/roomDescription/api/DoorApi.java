package com.java.roomDescription.api;

import com.java.roomDescription.model.Doors;
import retrofit2.Call;
import retrofit2.http.GET;

public interface DoorApi {
    @GET(".")
    Call<Doors> getInfoDoors();
}
