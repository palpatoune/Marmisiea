package com.example.marmisiea;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;

import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.widget.SearchView;

import com.example.marmisiea.adapters.OnRecipeListener;
import com.example.marmisiea.adapters.RecipeRecyclerAdapter;
import com.example.marmisiea.models.Recipe;

import com.example.marmisiea.util.Testing;
import com.example.marmisiea.util.VerticalSpacingItemDecorator;
import com.example.marmisiea.viewmodels.RecipeListViewModel;



import java.util.List;

public class RecipeListActivity extends BaseActivity implements OnRecipeListener {

  private static final String TAG = "RecipeListActivity";

  private RecipeListViewModel mRecipeListViewModel;
  private RecyclerView mRecyclerView;
  private RecipeRecyclerAdapter mAdapter;
  private SearchView mSearchView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_recipe_list);
    mRecyclerView = findViewById(R.id.recipe_list);
    mSearchView = findViewById(R.id.search_view);

    mRecipeListViewModel = ViewModelProviders.of(this).get(RecipeListViewModel.class);

    initRecyclerView();
    subscribeObservers();
    initSearchView();
    if(!mRecipeListViewModel.IsViewingRecipe()){
      //display the search category
      displaySearchCategories();
    }
    // associate custom toolbar with support action bar
    setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
  }

  private void subscribeObservers(){
    mRecipeListViewModel.getRecipe().observe(this, new Observer<List<Recipe>>() {
      @Override
      public void onChanged(@Nullable List<Recipe> recipes) {
        if(recipes != null){
          if(mRecipeListViewModel.IsViewingRecipe()){
            Testing.printRceipes(recipes, "network test");
            mRecipeListViewModel.setmIsPerformingQuery(false);
            mAdapter.setRecipes(recipes);
          }
        }
      }
    });
  }

  private void initRecyclerView(){
    mAdapter = new RecipeRecyclerAdapter(this);
    VerticalSpacingItemDecorator itemDecorator = new VerticalSpacingItemDecorator(30);
    mRecyclerView.addItemDecoration(itemDecorator);
    mRecyclerView.setAdapter(mAdapter);
    mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
      @Override
      public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
        if(!mRecyclerView.canScrollVertically(1)){
          mRecipeListViewModel.searchNextPage();
        }
      }
    });
  }

  private void initSearchView(){

    mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
      @Override
      public boolean onQueryTextSubmit(String query) {

        // Search the database for a recipe
        mAdapter.displayLoading();
        mRecipeListViewModel.searchRecipesApi("chicken", 1);
        mSearchView.clearFocus(); // loose text field focus when submiting request

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
    Intent intent = new Intent(this, RecipeActivity.class);
    intent.putExtra("recipe", mAdapter.getSelectedRecipe(position));
    startActivity(intent);
  }

  @Override
  public void onCategoryClick(String category) {
    mAdapter.displayLoading();
    Log.d(TAG, "onCategoryClick: " + category);
    mRecipeListViewModel.searchRecipesApi(category, 1);


  }

  private void displaySearchCategories(){
    mRecipeListViewModel.setIsViewingRecipe(false);
    mAdapter.displaySearchCategories();
  }


  @Override
  public void onBackPressed() {
    if(mRecipeListViewModel.onBackPressed()){
      super.onBackPressed();
    }
    else {
      displaySearchCategories();
    }

  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    if(item .getItemId()== R.id.action_categories){
      displaySearchCategories();
    }
    return super.onOptionsItemSelected(item);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.recipe_search_menu, menu);
    return super.onCreateOptionsMenu(menu);
  }
}
