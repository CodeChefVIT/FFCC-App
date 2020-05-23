package com.example.ffcc;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface Api {

        String BASE_URL = "https://ffcc-website.herokuapp.com/";

        @GET("time/app/{id}")
        Call<List<Teachers>> teachers(@Path("id") String a);
        @GET("time/subjectCode")
        Call<List<Subjects>> makel();
    }

