package com.example.user.mutualmobilechallenge.di.module;

import com.example.user.mutualmobilechallenge.data.remote.RemoteDataSource;
import com.example.user.mutualmobilechallenge.di.scope.ActivityScope;
import com.example.user.mutualmobilechallenge.di.scope.AppScope;
import com.example.user.mutualmobilechallenge.view.recipelist.RecipeListPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by user on 10/25/17.
 */

@Module
public class RecipeListModule {

//    Only have the presenter here since this is the only dependency we are passing in the
//    RecipeDetail activity
    @ActivityScope
    RecipeListPresenter provideRecipeListPresenter(RemoteDataSource remoteDataSource){
        return new RecipeListPresenter(remoteDataSource);
    }
}
