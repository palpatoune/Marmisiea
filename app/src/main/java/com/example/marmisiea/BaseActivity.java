package com.example.marmisiea;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

/***************************************************************************************************
                                       Why base activity ?

We use base activity to add some base functionality to other class only by extending base activity,
it's really useful when you have a bunch of method who will be re use. You will save time and line
in your code.
Kiss :*

***************************************************************************************************/
public abstract class BaseActivity extends AppCompatActivity {

  public ProgressBar mProgressBar; //we add it to base activity for re usability

  @Override
  public void setContentView(int layoutResID) {
    ConstraintLayout constraintLayout = (ConstraintLayout) getLayoutInflater().inflate(R.layout.activity_base,  null);
    FrameLayout frameLayout = constraintLayout.findViewById(R.id.activity_content);
    mProgressBar = constraintLayout.findViewById(R.id.progress_bar);

    getLayoutInflater().inflate(layoutResID, frameLayout, true);
    super.setContentView(constraintLayout);
  }

  public void showProgressBar(boolean visibility){
    mProgressBar.setVisibility(visibility ? View.VISIBLE : View.INVISIBLE); //Display or not the progress bar


  }


}
