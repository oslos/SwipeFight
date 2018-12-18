package com.example.oslos.swipefight;

import android.app.Activity;
import android.os.Bundle;

import android.view.MotionEvent;
import android.view.SurfaceView;
import android.widget.Toast;

public class GameActivity extends Activity {

	GameView _gameEngineView;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        

        SurfaceView view = new GameView(this, AppConstants.GetEngine());
        OnSwipeTouchListener ostl = new OnSwipeTouchListener(this);
        view.setOnTouchListener(ostl);
        setContentView(view);
        AppConstants.SetGameActivity(this);
    }
    @Override
    protected void onStop()
    {
        super.onStop();
        AppConstants.GetEngine().Reset();
    }

}
