package com.example.marmisiea;

import androidx.lifecycle.Observer;

import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import androidx.appcompat.widget.SearchView;

import com.example.marmisiea.adapters.OnRecipeListener;
import com.example.marmisiea.adapters.ReciperRecyclerAdapter;
import com.example.marmisiea.models.Recipe;

import com.example.marmisiea.util.Testing;
import com.example.marmisiea.viewmodels.RecipeListViewModel;



import java.util.List;

public class RecipeListActivity extends BaseActivity implements OnRecipeListener {  //By extension it still extend AppCompat

  private static final String TAG = "RecipeListActivity";
  private RecipeListViewModel mRecipeListViewModel;
  private RecyclerView mRecyclerView;
  private ReciperRecyclerAdapter mAdapter;

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


  private void subscribeObservers (){
    mRecipeListViewModel.getRecipe().observe(this, new Observer<List<Recipe>>() {
      @Override
      public void onChanged(List<Recipe> recipes) {
        if(recipes !=null){
          Testing.printRceipes(recipes, "recipes test");
          mAdapter.setRecipes(recipes);
        }
      }
    });
  }

  private void initRecyclerView(){
    mAdapter = new ReciperRecyclerAdapter( this);
    mRecyclerView.setAdapter(mAdapter);
    mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
  }



  private void initSearchView (){
    final SearchView searchView = findViewById(R.id.search_view);
    searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
      @Override
      public boolean onQueryTextSubmit(String query) {
        //when we submit, we actually use this one
        mRecipeListViewModel .searchRecipesApi(query,1);
        return false;
      }

      @Override
      public boolean onQueryTextChange(String newText) {
        //actualise digit by digit
        return false;
      }
    });
  }

  private void testRetrofitRequest(){

  }


  @Override
  public void onRecipeClick(int position) {

  }

  @Override
  public void onCategoryClick(String category) {

  }
}
