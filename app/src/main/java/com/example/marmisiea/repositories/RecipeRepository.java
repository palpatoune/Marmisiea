package com.example.marmisiea.repositories;

import com.example.marmisiea.models.Recipe;
import com.example.marmisiea.requests.RecipeApiClient;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class RecipeRepository {

  private static RecipeRepository instance;
  private RecipeApiClient mRecipeApiClient;

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

  public void searchRecipesApi(String query, int pageNumber){
    if(pageNumber == 0){
      pageNumber = 1;
    }
    mRecipeApiClient.searchRecipesApi(query, pageNumber);
  }
}

