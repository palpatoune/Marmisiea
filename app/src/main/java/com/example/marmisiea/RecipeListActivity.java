package com.example.marmisiea;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

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

public class RecipeListActivity extends BaseActivity {  //By extension it still extend AppCompat

  private static final String TAG = "RecipeListActivity";

  private RecipeListViewModel mRecipeListViewModel;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_recipe_list);

    mRecipeListViewModel = ViewModelProviders.of(this).get(RecipeListViewModel.class);

    subscribeObservers();
    findViewById(R.id.test).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        testRetrofitRequest();
      }
    });
  }


  private void subscribeObservers (){
    mRecipeListViewModel.getRecipe().observe(this, new Observer<List<Recipe>>() {
      @Override
      public void onChanged(List<Recipe> recipes) {
        if(recipes !=null){
          Testing.printRceipes(recipes, "recipes test");
        }
      }
    });
  }


  private void  searchRecipesApi(String query, int pageNumber){
    mRecipeListViewModel .searchRecipesApi(query,pageNumber);
  }

  private void testRetrofitRequest(){
    searchRecipesApi("chicken", 1);
  }


}
