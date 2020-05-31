package com.example.marmisiea;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.marmisiea.models.Recipe;
import com.example.marmisiea.viewmodels.RecipeViewModel;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;

public class RecipeActivity extends BaseActivity {

  private static final String TAG = "RecipeActivity";

  //Ui component
  private AppCompatImageView mRecipeImage;
  private TextView mRecipeTitle, mRecipeRank;
  private LinearLayout mRecipeIngredientsContainer;
  private ScrollView mScrollView;
  private RecipeViewModel mRecipeViewModel;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_recipe);
    mRecipeImage = findViewById(R.id.recipe_image);
    mRecipeTitle = findViewById(R.id.recipe_title);
    mRecipeRank = findViewById(R.id.recipe_social_score);
    mRecipeIngredientsContainer = findViewById(R.id.ingredients_container);
    mScrollView = findViewById(R.id.parent);

    mRecipeViewModel = ViewModelProviders.of(this).get(RecipeViewModel.class);

    showProgressBar(true);
    subscribeObservers();
    getIncomingIntent();


  }

  private void getIncomingIntent(){
    if(getIntent().hasExtra("recipe")){
      Recipe recipe = getIntent().getParcelableExtra("recipe");
      Log.d(TAG, "getIncomingIntent: " + recipe.getTitle());
      RecipeViewModel.searchRecipeById(recipe.getRecipe_id());
    }
  }

  private void subscribeObservers(){
    mRecipeViewModel.getRecipe().observe(this, new Observer<Recipe>() {
      @Override
      public void onChanged(Recipe recipe) {
        if(recipe!= null){
          if(recipe.getRecipe_id().equals(mRecipeViewModel.getRecipeId())){
            setRecipeProperties(recipe);
            mRecipeViewModel.setDidRetrieveRecipe(true);
          }
        }
      }
    });
    mRecipeViewModel.isRecipeRequestTimeout().observe(this, new Observer<Boolean>() {
      @Override
      public void onChanged(Boolean aBoolean) {
        if(aBoolean == true && !mRecipeViewModel.isDidRetrieveRecipe()==false){
          Log.d(TAG, "onChanged: timedout" );
        }
      }
    });
  }

  private void setRecipeProperties (Recipe recipe){
     if(recipe !=null){
       RequestOptions requestOptions = new RequestOptions()
               .placeholder(R.drawable.ic_launcher_background);

       Glide.with(this)
               .setDefaultRequestOptions(requestOptions)
               .load(recipe.getImage_url())
               .into(mRecipeImage);

       mRecipeTitle.setText(recipe.getTitle());
       mRecipeRank.setText(String.valueOf(Math.round(recipe.getSocial_rank())));

       mRecipeIngredientsContainer.removeAllViews();
       for(String ingredient : recipe.getIngredients()){

         TextView textView = new TextView(this);
         textView.setText(ingredient);
         textView.setTextSize(15);
         textView.setLayoutParams(new LinearLayout.LayoutParams(
                 ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
         ));
         mRecipeIngredientsContainer.addView(textView);
       }
     }
     showParentLayout();
     showProgressBar(false);
  }

  private void showParentLayout(){
    mScrollView.setVisibility(View.VISIBLE);
  }

}
