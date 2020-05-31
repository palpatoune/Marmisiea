package com.example.marmisiea.viewmodels;

import com.example.marmisiea.models.Recipe;
import com.example.marmisiea.repositories.RecipeRepository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class RecipeViewModel extends ViewModel {

  private static RecipeRepository mRecipeRepository;

  public RecipeViewModel(){
    mRecipeRepository = RecipeRepository.getInstance();
  }

  public LiveData<Recipe> getRecipe(){
    return mRecipeRepository.getRecipe();
  }

  public static void searchRecipeById(String recipeId){
    mRecipeRepository.searchRecipeById(recipeId );
  }

}
