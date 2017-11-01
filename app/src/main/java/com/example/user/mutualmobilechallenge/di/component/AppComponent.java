package com.example.user.mutualmobilechallenge.di.component;

import com.example.user.mutualmobilechallenge.data.local.DatabaseHelper;
import com.example.user.mutualmobilechallenge.data.remote.RemoteDataSource;
import com.example.user.mutualmobilechallenge.di.module.AppModule;
import com.example.user.mutualmobilechallenge.di.module.RecipeDetailModule;
import com.example.user.mutualmobilechallenge.di.module.RecipeListModule;
import com.example.user.mutualmobilechallenge.di.scope.AppScope;
import com.example.user.mutualmobilechallenge.view.recipelist.RecipeList;

import dagger.Component;

/**
 * Created by user on 10/25/17.
 */

@AppScope
@Component(modules = AppModule.class)
public interface AppComponent {


    RecipeListComponent plus(RecipeListModule recipeListModule);

    RecipeDetailComponent plus(RecipeDetailModule recipeDetailModule);


}
