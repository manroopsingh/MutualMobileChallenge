package com.example.user.mutualmobilechallenge.view.recipedetail;

import com.example.user.mutualmobilechallenge.model.Recipe;
import com.example.user.mutualmobilechallenge.utils.BasePresenter;
import com.example.user.mutualmobilechallenge.utils.BaseView;

/**
 * Created by user on 10/26/17.
 */

public interface RecipeDetailContract {

    interface View extends BaseView {
        void setToolbar(String image, String label);

        void setIngredients(String ingredients);

        void setSources(String sources, String sourceURL);
    }

    interface Presenter extends BasePresenter<View> {
        void initData(Recipe recipe);

    }

}
