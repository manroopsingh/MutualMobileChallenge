package com.example.user.mutualmobilechallenge.data.remote;

import com.example.user.mutualmobilechallenge.model.Response;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by user on 10/25/17.
 */

public class RemoteDataSource {

    public static final String APP_ID = "f6e40eb3";
    public static final String APP_KEY = "0fde0a5350c63784cc20690ebcda8185";

    private String BASE_URL;

    public RemoteDataSource(String BASE_URL) {
        this.BASE_URL = BASE_URL;
    }

    public  Retrofit create(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        return retrofit;

    }

    public  Observable<Response> getResponse(String query, String from, String to){
        Retrofit retrofit = create();
        RemoteService remoteService = retrofit.create(RemoteService.class);
        return remoteService.getRecipes(query, from, to, APP_ID, APP_KEY);

    }
}
