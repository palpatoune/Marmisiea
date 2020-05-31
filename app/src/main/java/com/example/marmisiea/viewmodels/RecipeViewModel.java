package com.example.marmisiea.viewmodels;

import com.example.marmisiea.models.Recipe;
import com.example.marmisiea.repositories.RecipeRepository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class RecipeViewModel extends ViewModel {

  private static RecipeRepository mRecipeRepository;
  private static String mRecipeId;
  private boolean mDidRetrieveRecipe  ;

  public RecipeViewModel(){
    mRecipeRepository = RecipeRepository.getInstance();
    mDidRetrieveRecipe = false;
  }

  public LiveData<Recipe> getRecipe(){
    return mRecipeRepository.getRecipe();
  }

  public LiveData<Boolean> isRecipeRequestTimeout(){
    return mRecipeRepository.isRecipeRequestTimeout();
  }

  public static void searchRecipeById(String recipeId){
    mRecipeId = recipeId;
    mRecipeRepository.searchRecipeById(recipeId );
  }

  public static String getRecipeId() {
    return mRecipeId;
  }

  public boolean isDidRetrieveRecipe() {
    return mDidRetrieveRecipe;
  }

  public void setDidRetrieveRecipe(boolean mDidRetrieveRecipe) {
    this.mDidRetrieveRecipe = mDidRetrieveRecipe;
  }
}
