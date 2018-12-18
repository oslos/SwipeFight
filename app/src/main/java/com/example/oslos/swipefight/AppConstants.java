package com.example.oslos.swipefight;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

public class AppConstants
{

    static BitmapBank _bitmapsBank;
    static GameEngine _engine;

    public static int SCREEN_WIDTH,
            SCREEN_HEIGHT;

    public static void Initialization(Context context)
    {
        _bitmapsBank = new BitmapBank(context.getResources());
        SetScreenSize(context);
        _engine = new GameEngine();
    }


    private static void SetScreenSize(Context context)
    {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;

        AppConstants.SCREEN_WIDTH = width;
        AppConstants.SCREEN_HEIGHT = height;
    }

    public static BitmapBank GetBitmapsBank()
    {
        return _bitmapsBank;
    }

    public static GameEngine GetEngine()
    {
        return _engine;
    }

    public static void StopThread(Thread thread)
    {
        boolean retry = true;
        while (retry)
        {
            try
            {
                thread.join();
                retry = false;
            }
            catch (InterruptedException e) {}
        }
    }

}
