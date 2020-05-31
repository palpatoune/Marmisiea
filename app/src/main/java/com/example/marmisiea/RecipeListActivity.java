package com.example.marmisiea;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.marmisiea.adapters.OnRecipeListener;
import com.example.marmisiea.adapters.ReciperRecyclerAdapter;
import com.example.marmisiea.models.Recipe;
import com.example.marmisiea.requests.RecipeApi;
import com.example.marmisiea.requests.ServiceGenerator;
import com.example.marmisiea.requests.responses.RecipeResponse;
import com.example.marmisiea.requests.responses.RecipeSearchResponse;
import com.example.marmisiea.util.Constants;
import com.example.marmisiea.util.Testing;
import com.example.marmisiea.viewmodels.RecipeListViewModel;

import java.io.IOException;
import java.util.ArrayList;
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
    testRetrofitRequest();
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

  private void  searchRecipesApi(String query, int pageNumber){
    mRecipeListViewModel .searchRecipesApi(query,pageNumber);
  }

  private void testRetrofitRequest(){
    searchRecipesApi("chicken", 1);
  }


  @Override
  public void onRecipeClick(int position) {

  }

  @Override
  public void onCategoryClick(String category) {

  }
}
