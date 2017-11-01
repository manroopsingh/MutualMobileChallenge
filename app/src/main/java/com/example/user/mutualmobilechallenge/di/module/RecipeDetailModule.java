package com.example.user.mutualmobilechallenge.di.module;

import com.example.user.mutualmobilechallenge.data.local.DatabaseHelper;
import com.example.user.mutualmobilechallenge.di.scope.ActivityScope;
import com.example.user.mutualmobilechallenge.view.recipedetail.RecipeDetailPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by user on 10/26/17.
 */

@Module
public class RecipeDetailModule {

//    Only have the presenter here since this is the only dependency we are passing in the
//    RecipeDetail activity
    @ActivityScope
    @Provides
    RecipeDetailPresenter provideRecipeDetailPresenter() {
        return new RecipeDetailPresenter();
    }




}
