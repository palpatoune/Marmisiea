package com.example.marmisiea.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.marmisiea.R;

import com.example.marmisiea.models.Recipe;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecipeRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

  private static final int recipeType =1;
  private static final int loadingType =2;
  private List<Recipe> mRecipes;
  private OnRecipeListener mOnRecipeListener;

  public RecipeRecyclerAdapter(OnRecipeListener mOnRecipeListener) {

    this.mOnRecipeListener = mOnRecipeListener;
    mRecipes = new ArrayList<>();
  }

  @NonNull
  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
    View view = null;
    switch (i){
      case recipeType: {
        view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_recipe_list_item, viewGroup, false);
        return new RecipeViewHolder(view, mOnRecipeListener);
      }
      case loadingType: {
        view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_loading_list_item, viewGroup, false);
        return new LoadingViewHolder(view);
      }
      default:{
        view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_recipe_list_item, viewGroup, false);
        return new RecipeViewHolder(view, mOnRecipeListener);
      }
    }
  }

  @Override
  public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

    int itemViewType = getItemViewType(i);
    if(itemViewType == recipeType) {
      // set the image
      RequestOptions options = new RequestOptions()
              .centerCrop()
              .error(R.drawable.ic_launcher_background);

      Glide.with(((RecipeViewHolder) viewHolder).itemView)
              .setDefaultRequestOptions(options)
              .load(mRecipes.get(i).getImage_url())
              .into(((RecipeViewHolder) viewHolder).image);

      ((RecipeViewHolder) viewHolder).title.setText(mRecipes.get(i).getTitle());
      ((RecipeViewHolder) viewHolder).publisher.setText(mRecipes.get(i).getPublisher());
      ((RecipeViewHolder) viewHolder).socialScore.setText(String.valueOf(Math.round(mRecipes.get(i).getSocial_rank())));
    }
  }

  @Override
  public int getItemViewType(int position) {
    if(mRecipes.get(position).getTitle().equals("LOADING...")){
      return loadingType;
    }
    else{
      return recipeType;
    }
  }

  public void displayLoading(){
    if(!isLoading()){
      Recipe recipe = new Recipe();
      recipe.setTitle("LOADING...");
      List<Recipe> loadingList = new ArrayList<>();
      loadingList.add(recipe);
      mRecipes = loadingList;
      notifyDataSetChanged();
    }
  }
  private boolean isLoading(){
    if(mRecipes.size() > 0){
      if(mRecipes.get(mRecipes.size() - 1).getTitle().equals("LOADING...")){
        return true;
      }
    }
    return false;
  }



  @Override
  public int getItemCount() {
    if (mRecipes != null) {
      return mRecipes.size();
    }
    return 0;
  }

  public void setRecipes(List<Recipe> recipes){
    mRecipes = recipes;
    notifyDataSetChanged();
  }

}
