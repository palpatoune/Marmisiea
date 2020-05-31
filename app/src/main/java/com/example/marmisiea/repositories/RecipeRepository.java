package com.example.marmisiea.repositories;

import com.example.marmisiea.models.Recipe;
import com.example.marmisiea.requests.RecipeApiClient;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class RecipeRepository {

  private static RecipeRepository instance;
  private RecipeApiClient mRecipeApiClient;
  private String mQuery;
  private int mPageNumber;

  public static RecipeRepository getInstance(){
    if(instance == null){
      instance = new RecipeRepository();
    }
    return instance;
  }

  private RecipeRepository() {
    mRecipeApiClient = RecipeApiClient.getInstance();
  }

  public LiveData<List<Recipe>> getRecipes(){
    return mRecipeApiClient.getRecipes();
  }
  public LiveData<Recipe> getRecipe(){
    return mRecipeApiClient.getRecipe();
  }

  public void searchRecipeById (String recipeId){
    mRecipeApiClient.searchRecipeById(recipeId);
  }

  public void searchRecipesApi(String query, int pageNumber){
    if(pageNumber == 0){
      pageNumber = 1;
    }
    mQuery = query;
    mPageNumber = pageNumber;
    mRecipeApiClient.searchRecipesApi(query, pageNumber);
  }

  public void searchNextPage(){
    searchRecipesApi(mQuery, mPageNumber+1);
  }

  public void cancelRequest(){
    mRecipeApiClient.cancelRequest();
  }
}

