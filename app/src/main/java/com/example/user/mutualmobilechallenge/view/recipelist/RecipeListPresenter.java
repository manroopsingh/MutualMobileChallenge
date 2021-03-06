package com.example.user.mutualmobilechallenge.view.recipelist;

import android.util.Log;

import com.example.user.mutualmobilechallenge.data.remote.RemoteDataSource;
import com.example.user.mutualmobilechallenge.model.HitsItem;
import com.example.user.mutualmobilechallenge.model.Recipe;
import com.example.user.mutualmobilechallenge.model.Response;
import com.example.user.mutualmobilechallenge.utils.TagManager;
import com.example.user.mylibrary.SampleClass;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by user on 10/25/17.
 */

public class RecipeListPresenter implements RecipeListContact.Presenter {

    static final int INIT_ITEMS = 0;

    private RemoteDataSource remoteDataSource;
    private RecipeListContact.View view;
    private List<Recipe> recipeList = new ArrayList<>();

    @Inject
    public RecipeListPresenter(RemoteDataSource remoteDataSource) {
        this.remoteDataSource = remoteDataSource;

    }

    @Override
    public void attachView(RecipeListContact.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;

    }

    @Override
    public void getRecipes(int fromItemNumber, String query) {

        Log.d(TagManager.get(this), "getRecipes: " + fromItemNumber + ":" + query);

        if (fromItemNumber == INIT_ITEMS)
            view.showProgress(INIT_ITEMS);
        else
            view.showProgress(fromItemNumber);


        Observer<Recipe> observer = new Observer<Recipe>() {
            @Override
            public void onSubscribe(Disposable d) {

                Log.d(TagManager.get(this), "onSubscribe: ");
            }

            @Override
            public void onNext(Recipe recipe) {

                recipeList.add(recipe);
            }

            @Override
            public void onError(Throwable e) {
                view.showError(e.getMessage());
            }

            @Override
            public void onComplete() {
                view.updateRecipes(recipeList);
            }
        };

        String from = String.valueOf(fromItemNumber);
        String to = String.valueOf(fromItemNumber + 20);
        remoteDataSource.getResponse(query, from, to)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .flatMap(getFlatMapFunction())
                .subscribe(observer);


    }

    private Function<Response, Observable<Recipe>> getFlatMapFunction() {
        return new Function<Response, Observable<Recipe>>() {
            @Override
            public Observable<Recipe> apply(final Response response) throws Exception {

                return Observable.create(new ObservableOnSubscribe<Recipe>() {
                    @Override
                    public void subscribe(ObservableEmitter<Recipe> e) throws Exception {

                        for (HitsItem hitsItem : response.getHits()) {
                            e.onNext(hitsItem.getRecipe());
                        }
                        e.onComplete();


                    }
                });
            }
        };
    }

}
