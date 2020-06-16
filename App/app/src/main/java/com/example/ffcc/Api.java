package com.example.ffcc;

import com.google.android.gms.common.data.DataBufferObserver;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface Api {
        String BASE_URL = "https://ffcc-website.herokuapp.com/";
        @GET("time/app/{id}")
        io.reactivex.Observable<List<Teachers>> teachers(@Path("id") String a);
        @GET("time/subjectCode")
        Call<List<Subjects>> makel();
    }

