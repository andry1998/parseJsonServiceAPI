package com.java.roomDescription.controller;

import com.java.roomDescription.api.API;
import org.springframework.stereotype.Controller;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Controller
public class RetrofitController {
    public static final String HOST = "http://cars.cprogroup.ru/api/rubetek/";
    Retrofit retrofit = null;
    private Retrofit getRetrofit() {
        if(retrofit == null)
            retrofit = new Retrofit.Builder()
                    .baseUrl(HOST)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        return retrofit;
    }
    public API createAPI() {
        return getRetrofit().create(API.class);
    }
}
