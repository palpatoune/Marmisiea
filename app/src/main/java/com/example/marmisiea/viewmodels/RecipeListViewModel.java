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
  private boolean mIsPerformingQuery;


  public RecipeListViewModel() {

    mRecipeRepository = RecipeRepository.getInstance();
    mIsPerformingQuery = false;
  }

  public LiveData<List<Recipe>> getRecipe(){
    return mRecipeRepository.getRecipes();
  }

  public void  searchRecipesApi(String query, int pageNumber){
    mIsViewingRecipes = true;
    mIsPerformingQuery = true;
    mRecipeRepository .searchRecipesApi(query,pageNumber);
  }

  public boolean IsViewingRecipe() {
    return mIsViewingRecipes;
  }

  public void setIsViewingRecipe(boolean mIsViewingRecipe) {
    mIsViewingRecipes = mIsViewingRecipe;
  }

  public boolean onBackPressed(){
    if (isPerformingQuery()){
      //cancel the query
      mRecipeRepository.cancelRequest();
      mIsPerformingQuery = false;
    }
    if(mIsViewingRecipes){
      mIsViewingRecipes = false;
      return false;
    }
    return true;
  }

  public boolean isPerformingQuery() {
    return mIsPerformingQuery;
  }

  public void setmIsPerformingQuery(boolean IsPerformingQuery) {
    mIsPerformingQuery = IsPerformingQuery;
  }
}
