package com.example.user.mutualmobilechallenge.view.recipelist;

import com.example.user.mutualmobilechallenge.model.Recipe;
import com.example.user.mutualmobilechallenge.utils.BasePresenter;
import com.example.user.mutualmobilechallenge.utils.BaseView;

import java.util.List;

/**
 * Created by user on 10/25/17.
 */

public interface RecipeListContact {

    interface View extends BaseView{

        void updateRecipes(List<Recipe> recipeList);
        void showProgress(int MODE);
    }

    interface Presenter extends BasePresenter<View>{
        void getRecipes(int page, String query);


    }
}
