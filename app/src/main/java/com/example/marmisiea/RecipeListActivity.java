package com.example.marmisiea;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;

import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.widget.SearchView;

import com.example.marmisiea.adapters.OnRecipeListener;
import com.example.marmisiea.adapters.RecipeRecyclerAdapter;
import com.example.marmisiea.models.Recipe;

import com.example.marmisiea.util.Testing;
import com.example.marmisiea.viewmodels.RecipeListViewModel;



import java.util.List;

public class RecipeListActivity extends BaseActivity implements OnRecipeListener {

  private static final String TAG = "RecipeListActivity";

  private RecipeListViewModel mRecipeListViewModel;
  private RecyclerView mRecyclerView;
  private RecipeRecyclerAdapter mAdapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_recipe_list);
    mRecyclerView = findViewById(R.id.recipe_list);

    mRecipeListViewModel = ViewModelProviders.of(this).get(RecipeListViewModel.class);

    initRecyclerView();
    subscribeObservers();
    initSearchView();
  }

  private void subscribeObservers(){

    mRecipeListViewModel.getRecipe().observe(this, new Observer<List<Recipe>>() {
      @Override
      public void onChanged(@Nullable List<Recipe> recipes) {
        if(recipes != null){
          Testing.printRceipes(recipes, "network test");
        }
        mAdapter.setRecipes(recipes);
      }
    });
  }

  private void initRecyclerView(){
    mAdapter = new RecipeRecyclerAdapter(this);
    mRecyclerView.setAdapter(mAdapter);
    mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
  }

  private void initSearchView(){
    final SearchView searchView = findViewById(R.id.search_view);
    searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
      @Override
      public boolean onQueryTextSubmit(String query) {

        // Search the database for a recipe
        mAdapter.displayLoading();
        mRecipeListViewModel.searchRecipesApi("chicken", 1);

        return false;
      }

      @Override
      public boolean onQueryTextChange(String query) {

        // Wait for the user to submit the search. So do nothing here.

        return false;
      }
    });
  }

  @Override
  public void onRecipeClick(int position) {
    Log.d(TAG, "onRecipeClick: clicked. " + position);
  }

  @Override
  public void onCategoryClick(String category) {

  }
}
