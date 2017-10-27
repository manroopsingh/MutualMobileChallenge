package com.example.user.mutualmobilechallenge.di.component;

import com.example.user.mutualmobilechallenge.data.remote.RemoteDataSource;
import com.example.user.mutualmobilechallenge.di.module.RecipeListModule;
import com.example.user.mutualmobilechallenge.di.scope.ActivityScope;
import com.example.user.mutualmobilechallenge.di.scope.AppScope;
import com.example.user.mutualmobilechallenge.view.recipelist.RecipeList;

import dagger.Subcomponent;

/**
 * Created by user on 10/25/17.
 */

@ActivityScope
@Subcomponent(modules = RecipeListModule.class)
public interface RecipeListComponent {

    void inject(RecipeList recipeList);
    RemoteDataSource remoteSource();

}
