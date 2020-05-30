package com.example.marmisiea.viewmodels;

import com.example.marmisiea.models.Recipe;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RecipeListViewModel extends ViewModel {

  private MutableLiveData<List<Recipe>> mRecipe = new MutableLiveData<>();

  public RecipeListViewModel() {

  }

  public LiveData<List<Recipe>> getRecipe(){
    return mRecipe;
  }

}
