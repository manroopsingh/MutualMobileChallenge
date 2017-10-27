package com.example.user.mutualmobilechallenge;

import android.app.Application;
import android.content.Context;

import com.example.user.mutualmobilechallenge.di.component.AppComponent;
import com.example.user.mutualmobilechallenge.di.component.DaggerAppComponent;
import com.example.user.mutualmobilechallenge.di.component.RecipeDetailComponent;
import com.example.user.mutualmobilechallenge.di.component.RecipeListComponent;
import com.example.user.mutualmobilechallenge.di.module.AppModule;
import com.example.user.mutualmobilechallenge.di.module.RecipeDetailModule;
import com.example.user.mutualmobilechallenge.di.module.RecipeListModule;
import com.example.user.mutualmobilechallenge.model.Recipe;

/**
 * Created by user on 10/26/17.
 */

public class RecipeApplication extends Application {

    private AppComponent appComponent;
    private RecipeListComponent recipeListComponent;
    public static final String BASEURL = "https://api.edamam.com/";
    private RecipeDetailComponent recipeDetailComponent;

    @Override
    public void onCreate() {
        super.onCreate();


        AppModule appModule = new AppModule(BASEURL);
        appComponent = DaggerAppComponent.builder()
                .appModule(appModule)
                .build();

    }

    public static RecipeApplication get(Context context) {
        return (RecipeApplication) context.getApplicationContext();
    }


    public RecipeListComponent getRecipeListComponent() {

        recipeListComponent = appComponent.plus(new RecipeListModule());
        return recipeListComponent;
    }
    public RecipeDetailComponent getRecipeDetailComponent() {

        recipeDetailComponent = appComponent.plus(new RecipeDetailModule());
        return recipeDetailComponent;
    }

    public void clearRecipeListComponent() {
        recipeListComponent = null;

    }
    public void clearRecipeDetailComponent() {
        recipeListComponent = null;

    }


}
