package com.allanim.movieinfo.api;

import com.allanim.movieinfo.models.MovieDetail;
import com.allanim.movieinfo.models.SearchResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OmdbApi {

    @GET("/")
    Call<SearchResult> searchMovie(@Query("s") String name, @Query("apikey") String apiKey);

    @GET("/")
    Call<MovieDetail> getMovie(@Query("i") String id, @Query("apikey") String apiKey);
}
