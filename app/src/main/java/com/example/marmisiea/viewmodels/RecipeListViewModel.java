package com.example.marmisiea.viewmodels;

import com.example.marmisiea.models.Recipe;
import com.example.marmisiea.repositories.RecipeRepository;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RecipeListViewModel extends ViewModel {

  private boolean mIsViewingRecipes;
  private RecipeRepository mRecipeRepository;

  public RecipeListViewModel() {
    mIsViewingRecipes = false;
    mRecipeRepository = RecipeRepository.getInstance();

  }

  public LiveData<List<Recipe>> getRecipe(){
    return mRecipeRepository.getRecipes();
  }

  public void  searchRecipesApi(String query, int pageNumber){
    mIsViewingRecipes = true;
    mRecipeRepository .searchRecipesApi(query,pageNumber);
  }

  public boolean IsViewingRecipe() {
    return mIsViewingRecipes;
  }

  public void setIsViewingRecipe(boolean mIsViewingRecipe) {
    mIsViewingRecipes = mIsViewingRecipe;
  }
}
