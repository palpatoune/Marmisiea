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

public class ReciperRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

  private List<Recipe> mRecipes;
  private OnRecipeListener mOnRecipeListener;

  public ReciperRecyclerAdapter( OnRecipeListener mOnRecipeListener) {

    this.mOnRecipeListener = mOnRecipeListener;
    mRecipes = new ArrayList<>();
  }

  @NonNull
  @Override
  public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
    View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_recipe_list_item, viewGroup, false);
    return new RecipeViewHolder(view, mOnRecipeListener);
  }

  @Override
  public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
    //set the image
    RequestOptions requestOptions = new RequestOptions()
            .placeholder(R.drawable.ic_launcher_background);
    Glide.with(viewHolder.itemView.getContext())
            .setDefaultRequestOptions(requestOptions)
            .load(mRecipes.get(i).getImage_url())
            .into(((RecipeViewHolder)viewHolder).image);
    ((RecipeViewHolder)viewHolder).title.setText(mRecipes.get(i).getTitle());
    ((RecipeViewHolder)viewHolder).publisher.setText(mRecipes.get(i).getPublisher());
    ((RecipeViewHolder)viewHolder).socialScore.setText(String.valueOf(Math.round(mRecipes.get(i).getSocial_rank())));
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
