package com.example.marmisiea.util;

import android.util.Log;

import com.example.marmisiea.models.Recipe;

import java.util.List;



public class Testing {
  public static void printRceipes (List<Recipe>list, String tag){
    for (Recipe recipe: list){
      Log.d(tag, "onChanged: "+recipe.getTitle());
    }
  }
}
