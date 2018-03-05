package com.example.user.mutualmobilechallenge.view.recipedetail;

import com.example.user.mutualmobilechallenge.model.Recipe;

import java.util.List;

/**
 * Created by user on 10/26/17.
 */

public class RecipeDetailPresenter implements RecipeDetailContract.Presenter {

    RecipeDetailContract.View view;


    @Override
    public void attachView(RecipeDetailContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }

    @Override
    public void initData(Recipe recipe) {


        view.setToolbar(recipe.getImage(), recipe.getLabel());
        view.setIngredients(formattedIngredients(recipe.getIngredientLines()));
        view.setSources(recipe.getSource(),recipe.getUrl());



    }

    public String formattedIngredients(List<String> ingredientLines) {
        StringBuilder stringBuilder = new StringBuilder();

        for (String s : ingredientLines) {
            stringBuilder.append(s + "\n");

        }

        return stringBuilder.toString();
    }
}
