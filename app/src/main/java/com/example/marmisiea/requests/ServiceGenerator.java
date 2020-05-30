package com.example.marmisiea.requests;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import com.example.marmisiea.util.Constants;

/**

 Singleton mean that only one instance of an object can live at a time

 */
public class ServiceGenerator {

  private static Retrofit.Builder retrofitBuilder = //instantiate retrofit instance
          new Retrofit.Builder()
                  .baseUrl(Constants.BASE_URL)
                  .addConverterFactory(GsonConverterFactory.create()); // to convert our http call result

  private static Retrofit retrofit = retrofitBuilder.build(); // we want to return an instance of this object

  private  static RecipeApi recipeApi = retrofit.create(RecipeApi.class); // should be static

  public static RecipeApi getRecipeApi(){
    return recipeApi;
  }


}
