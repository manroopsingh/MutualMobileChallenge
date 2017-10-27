package com.example.user.mutualmobilechallenge.di.component;

import com.example.user.mutualmobilechallenge.di.module.RecipeDetailModule;
import com.example.user.mutualmobilechallenge.di.scope.ActivityScope;
import com.example.user.mutualmobilechallenge.view.recipedetail.RecipeDetail;

import javax.inject.Inject;

import dagger.Component;
import dagger.Subcomponent;

/**
 * Created by user on 10/26/17.
 */

@ActivityScope
@Subcomponent(modules = RecipeDetailModule.class)
public interface RecipeDetailComponent {

    void inject(RecipeDetail recipeDetail);

}
