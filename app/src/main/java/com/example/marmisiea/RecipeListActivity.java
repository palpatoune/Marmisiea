package com.example.marmisiea;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class RecipeListActivity extends BaseActivity {  //By extension it still extend AppCompat

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_recipe_list);

    findViewById(R.id.test).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (mProgressBar.getVisibility() == View.VISIBLE ){
          showProgressBar(false);
        }
        else {
          showProgressBar(true);
        }
      }
    });
  }
}
