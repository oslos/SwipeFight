package com.example.oslos.swipefight;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.SurfaceHolder;

public class DisplayThread extends Thread
{
    SurfaceHolder _surfaceHolder;
    Paint _backgroundPaint;

    long _sleepTime;


    final long  DELAY = 4;

    boolean  _isOnRun;

    public DisplayThread(SurfaceHolder surfaceHolder, Context context)
    {
        _surfaceHolder = surfaceHolder;


        _backgroundPaint = new Paint();
        _backgroundPaint.setARGB(255, 0, 0, 0);
        _isOnRun = true;
    }

    @Override
    public void run()
    {

        while (_isOnRun)
        {

            AppConstants.GetEngine().Update();


            Canvas canvas = _surfaceHolder.lockCanvas(null);

            if (canvas != null)
            {

                synchronized (_surfaceHolder)
                {
                    canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), _backgroundPaint);
                    AppConstants.GetEngine().Draw(canvas);
                }


                _surfaceHolder.unlockCanvasAndPost(canvas);
            }


            try
            {
                Thread.sleep(DELAY);
            }
            catch (InterruptedException ex)
            {

            }
        }
    }

    public boolean IsRunning()
    {
        return _isOnRun;
    }
    public void SetIsRunning(boolean state)
    {
        _isOnRun = state;
    }
}