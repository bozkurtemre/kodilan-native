package com.kodilan.service;

import com.kodilan.requests.getRecentJobs;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {

    @GET("posts")
    Call<getRecentJobs> getRecentJobs (
            @Query("get") int get,
            @Query("period") String period
    );

}
