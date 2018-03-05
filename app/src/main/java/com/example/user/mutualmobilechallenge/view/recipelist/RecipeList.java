package com.example.user.mutualmobilechallenge.view.recipelist;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.example.user.mutualmobilechallenge.R;
import com.example.user.mutualmobilechallenge.RecipeApplication;
import com.example.user.mutualmobilechallenge.model.Recipe;
import com.example.user.mutualmobilechallenge.utils.TagManager;
import com.example.user.mutualmobilechallenge.view.recipedetail.RecipeDetail;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class RecipeList extends AppCompatActivity implements RecipeListContact.View, RecipeListAdapter.RecipeListItemListener {


    private List<Recipe> recipeList = new ArrayList<>();

    @Inject
    RecipeListPresenter presenter;
    private RecyclerView rvRecipeList;
    private RecyclerView.LayoutManager layoutManager;
    private RecipeListAdapter adapter;
    private ProgressDialog progress;
    private RecipeListScrollListener scrollListener;
    private String query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupDaggerComponent();
        presenter.attachView(this);
        setupRecipeRecyclerView();
        addonScrollListener();

        query = "chicken";
        presenter.getRecipes(0, query);


        Toolbar toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =  findViewById(R.id.mySearchView);

        // Assumes current activity is the searchable activity
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false); // Do not iconify the widget; expand it by default



    }
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            query = intent.getStringExtra(SearchManager.QUERY);

            recipeList.clear();
            scrollListener.resetState();
            Log.d(TagManager.get(this), "onNewIntent: ");
            presenter.getRecipes(0, query);
        }

        //hide keyboard after search
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);

        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);

    }

    private void setupDaggerComponent() {
        RecipeApplication.get(this).getRecipeListComponent().inject(this);
    }

    private void setupRecipeRecyclerView() {
        rvRecipeList = findViewById(R.id.rvRecipeList);
        layoutManager = new GridLayoutManager(this, 3);
        rvRecipeList.setLayoutManager(layoutManager);
        adapter = new RecipeListAdapter(this, recipeList);
        rvRecipeList.setAdapter(adapter);
    }

    private void addonScrollListener() {
        scrollListener = new RecipeListScrollListener((GridLayoutManager) layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {

                presenter.getRecipes(totalItemsCount, query);
            }
        };
        rvRecipeList.addOnScrollListener(scrollListener);
    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void updateRecipes(List<Recipe> recipeList1) {
        progress.dismiss();
        this.recipeList.addAll(recipeList1);
        adapter.notifyDataSetChanged();

    }

    @Override
    public void showProgress(int MODE) {
        if(MODE == RecipeListPresenter.INIT_ITEMS){
            progress = new ProgressDialog(this);
            progress.setTitle("Loading");
            progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
            progress.show();
        }
        else{
            Toast.makeText(this, "Loading more items..", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onItemClicked(Recipe recipe) {

        Intent intent = new Intent(this, RecipeDetail.class);
        intent.putExtra("recipe", recipe);
        startActivity(intent);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
        RecipeApplication.get(this).clearRecipeListComponent();
    }
}
