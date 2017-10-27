package com.example.user.mutualmobilechallenge.di.module;

import com.example.user.mutualmobilechallenge.data.remote.RemoteDataSource;
import com.example.user.mutualmobilechallenge.di.scope.AppScope;
import com.example.user.mutualmobilechallenge.view.recipelist.RecipeListPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by user on 10/25/17.
 */

@Module
public class AppModule {


    String BASE_URL;

    public AppModule(String BASE_URL) {
        this.BASE_URL = BASE_URL;
    }

    @AppScope
    @Provides
    RemoteDataSource provideRemoteDataSource(){
        return new RemoteDataSource(BASE_URL);
    }

}
