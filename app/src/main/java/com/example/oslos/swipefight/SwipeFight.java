package com.example.oslos.swipefight;

import android.app.Application;

public class SwipeFight extends Application {


    public SwipeFight()
    {

    }

    @Override
    public void onCreate() {
        super.onCreate();
        AppConstants.Initialization(this.getApplicationContext(),getSharedPreferences("prefs",MODE_PRIVATE));

    }
}