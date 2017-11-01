package com.example.user.mutualmobilechallenge.di.module;

import android.content.Context;

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
    Context context;

    public AppModule(String BASE_URL, Context context) {
        this.BASE_URL = BASE_URL;
        this.context = context;
    }

//    Pass the dependencies that are used in sub-components on the this components.
    @AppScope
    @Provides
    RemoteDataSource provideRemoteDataSource(){
        return new RemoteDataSource(BASE_URL, context);
    }

    @AppScope
    @Provides
    Context providesContext(){
        return this.context;
    }



}
