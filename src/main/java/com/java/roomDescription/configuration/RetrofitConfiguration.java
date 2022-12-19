package com.java.roomDescription.configuration;

import com.java.roomDescription.api.API;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Configuration
public class RetrofitConfiguration {

    @Value("${sync.cars.host}")
    String host;

    @Bean("cars")
    public Retrofit create() {
        return new Retrofit.Builder()
                .baseUrl(host)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Bean
    public API createAPI(@Qualifier("cars") Retrofit retrofit) {
        return retrofit.create(API.class);
    }
}
