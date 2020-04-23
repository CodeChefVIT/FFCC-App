package com.example.ffcc;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface Api {

        String BASE_URL = "https://ffcc-website.herokuapp.com/";
        @GET("time")
        Call<List<Teachers>> teachers(@Query("PHY1701")String order);

    }

