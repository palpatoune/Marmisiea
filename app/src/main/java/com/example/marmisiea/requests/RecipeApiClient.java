package com.example.marmisiea.requests;

import com.example.marmisiea.AppExecutors;
import com.example.marmisiea.models.Recipe;

import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import static com.example.marmisiea.util.Constants.NETWORK_TIMEOUT;

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

  public void searchRecipesApi(){
    final Future handler = AppExecutors.getInstance().NetworkIO().submit(new Runnable() {
      @Override
      public void run() {
        //retrieve data from REST API
        //mRecipes.postValue();
      }
    });
     AppExecutors.getInstance().NetworkIO().schedule(new Runnable() {
       @Override
       public void run() {
         //let the user know it's timed out
         handler.cancel(true);
       }
     }, NETWORK_TIMEOUT, TimeUnit.MILLISECONDS);
  }
}
