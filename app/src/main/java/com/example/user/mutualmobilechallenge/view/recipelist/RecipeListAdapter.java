package com.example.user.mutualmobilechallenge.view.recipelist;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.user.mutualmobilechallenge.R;
import com.example.user.mutualmobilechallenge.model.Recipe;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 10/26/17.
 */

public class RecipeListAdapter extends RecyclerView.Adapter<RecipeListAdapter.ViewHolder> {

    private Context context;
    private List<Recipe> recipeList = new ArrayList<>();
    private RecipeListItemListener listener;

    public RecipeListAdapter(Context context, List<Recipe> recipeList) {
        this.context = context;
        this.listener = (RecipeListItemListener) context;
        this.recipeList = recipeList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_list_item
                , parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Recipe recipe = recipeList.get(position);
        Picasso.with(context).load(recipe.getImage()).into(holder.recipeImage);

    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView recipeImage;

        public ViewHolder(View itemView) {
            super(itemView);
            recipeImage = itemView.findViewById(R.id.recipeImage);
            itemView.setOnClickListener(this);


        }

        @Override
        public void onClick(View view) {
            listener.onItemClicked(recipeList.get(getLayoutPosition()));

        }
    }


    interface RecipeListItemListener {
        void onItemClicked(Recipe recipe);
    }
}
