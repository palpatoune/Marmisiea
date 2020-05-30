package com.example.marmisiea.requests;

import com.example.marmisiea.requests.responses.RecipeResponse;
import com.example.marmisiea.requests.responses.RecipeSearchResponse;

import java.security.Key;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RecipeApi {

  //SEARCH
  @GET("api/search") //it will use the base url define in constants
  Call<RecipeSearchResponse> searchRecipe(
          @Query("Key") String Key, // ?
          @Query("q") String query, // &
          @Query("page") String page // &
  );

  //GET RECIPE REQUEST
  @GET("api/get")
  Call<RecipeResponse> getRecipe(
          @Query("Key") String Key, // ?
          @Query("rId") String recipe_id // &
  );


}
