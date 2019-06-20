package com.nytsample.listeners;

import com.nytsample.model.MostPopularResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIListner {

    @GET("mostpopular/v2/mostviewed/{section}/{period}.json")
    Call<MostPopularResponse> getNewsDetails(@Path("section") String section, @Path("period") String period,
                                             @Query("api-key") String apiKey);
}
