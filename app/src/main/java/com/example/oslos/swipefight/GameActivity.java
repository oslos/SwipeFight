package com.example.oslos.swipefight;

import android.app.Activity;
import android.os.Bundle;

import android.view.MotionEvent;
import android.view.SurfaceView;

public class GameActivity extends Activity {

	GameView _gameEngineView;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        

        SurfaceView view = new GameView(this, AppConstants.GetEngine());
        setContentView(view);

    }

	@Override
	public boolean onTouchEvent(MotionEvent event) 
	{
		 super.onTouchEvent(event);
		int action = event.getAction();
		switch (action) 
		{
			case MotionEvent.ACTION_DOWN:
			{
				OnActionDown(event);
				break;
			}
			case MotionEvent.ACTION_UP:
			{
				OnActionUp(event);
				break;
			}
			case MotionEvent.ACTION_MOVE:
			{
				OnActionMove(event);
				break;
			}
			default:break;
		}
		return false;
	}
	

	private void OnActionMove(MotionEvent event) 
	{
		int x = (int)event.getX();
		int y = (int)event.getY();

		
		AppConstants.GetEngine().SetLastTouch(event.getX(), event.getY());
	}
	private void OnActionUp(MotionEvent event)
	{
		int x = (int)event.getX();
		int y = (int)event.getY();

	}

	private void OnActionDown(MotionEvent event) 
	{
		 AppConstants.GetEngine().SetLastTouch(event.getX(), event.getY());
	}

}
