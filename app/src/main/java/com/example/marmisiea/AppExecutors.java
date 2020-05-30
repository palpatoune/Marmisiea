package com.example.marmisiea;

import com.google.gson.annotations.Expose;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class AppExecutors {

  private static AppExecutors instance;

  public static AppExecutors getInstance(){
    if (instance == null){
      instance = new AppExecutors();
    }
    return instance;
  }

  //schedule commands to run after a certain delay.
  private final ScheduledExecutorService mNetworkIO = Executors.newScheduledThreadPool(3); // creat a pool of 3 thread to do all the work

  public ScheduledExecutorService NetworkIO(){
    return mNetworkIO;
  }
}
