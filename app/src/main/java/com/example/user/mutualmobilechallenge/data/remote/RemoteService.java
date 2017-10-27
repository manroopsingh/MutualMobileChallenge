package com.example.user.mutualmobilechallenge.data.remote;

import com.example.user.mutualmobilechallenge.model.Response;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by user on 10/25/17.
 */

public interface RemoteService {


    @GET("search")
    Observable<Response> getRecipes(
            @Query("q") String query
            , @Query("from") String from
            , @Query("to") String to
            , @Query("app_id") String app_id
            , @Query("app_key") String app_key);

}
