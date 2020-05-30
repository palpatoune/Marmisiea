package com.example.marmisiea.requests;

import com.example.marmisiea.models.Recipe;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class RecipeApiClient {

  private static RecipeApiClient instance;
  private MutableLiveData<List<Recipe>> mRecipes;

  public static RecipeApiClient getInstance(){
    if (instance == null){
      instance = new RecipeApiClient();
    }
    return instance;
  }

  private RecipeApiClient(){
    mRecipes = new MutableLiveData<>();
  }

  public LiveData<List<Recipe>> getRecipes(){
    return mRecipes;
  }
}
