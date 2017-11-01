package com.example.user.mutualmobilechallenge.view.recipedetail;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.mutualmobilechallenge.R;
import com.example.user.mutualmobilechallenge.RecipeApplication;
import com.example.user.mutualmobilechallenge.model.Recipe;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

public class RecipeDetail extends AppCompatActivity implements RecipeDetailContract.View {


    @Inject
    RecipeDetailPresenter presenter;

    private Toolbar toolbar;
    private TextView tvIngredients;
    private TextView tvSourceName;
    private TextView tvSourceURL;
    private ImageView ivRecipeImage;
    private TextView tvRecipeLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);


        setupDaggerComponent();

        presenter.attachView(this);
        bindViews();

        setSupportActionBar(toolbar);
        presenter.initData(getRecipe());

    }

    private void setupDaggerComponent() {
        RecipeApplication.get(this).getRecipeDetailComponent().inject(this);
    }

    private Recipe getRecipe() {
        Recipe recipe = (Recipe) getIntent().getSerializableExtra("recipe");
        return recipe;
    }

    private void bindViews() {
        toolbar = findViewById(R.id.toolbar);
        tvIngredients = findViewById(R.id.tvIngredients);
        tvSourceName = findViewById(R.id.tvSourceName);
        tvSourceURL = findViewById(R.id.tvSourceURL);
        ivRecipeImage = findViewById(R.id.ivRecipeImage);
        tvRecipeLabel = findViewById(R.id.tvRecipeLabel);
    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void setToolbar(String image, String label) {
        Picasso.with(this).load(image).into(ivRecipeImage);
        tvRecipeLabel.setText(label);
    }

    @Override
    public void setIngredients(String ingredients) {

        tvIngredients.setText(ingredients);
    }

    @Override
    public void setSources(String sourceName, final String sourceURL) {

        tvSourceName.setText(sourceName);
        tvSourceURL.setText(sourceURL);
        tvSourceURL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri webpage = Uri.parse(sourceURL);
                Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
        RecipeApplication.get(this).clearRecipeDetailComponent();

    }
}
