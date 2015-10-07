package com.nookdev.bigdigtestapp.network;


import com.nookdev.bigdigtestapp.model.Response;

import retrofit.Call;
import retrofit.http.GET;

public interface Api {
    @GET("home/video")
    Call<Response> getData();
}
