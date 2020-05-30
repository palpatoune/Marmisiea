package com.example.marmisiea.viewmodels;

import com.example.marmisiea.models.Recipe;
import com.example.marmisiea.repositories.RecipeRepository;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RecipeListViewModel extends ViewModel {

  private RecipeRepository mRecipeRepository;

  public RecipeListViewModel() {
    mRecipeRepository = RecipeRepository.getInstance();
  }

  public LiveData<List<Recipe>> getRecipe(){
    return mRecipeRepository.getRecipes();
  }

  public void  searchRecipesApi(String query, int pageNumber){
    mRecipeRepository .searchRecipesApi(query,pageNumber);
  }

}
