package com.example.oslos.swipefight;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import com.example.oslos.swipefight.objects.MainCharacter;

public class GameEngine
{
    static MainCharacter _main_character;
    static final Object _sync = new Object();
    static float _lastTouchedX, _lastTouchedY;

    public GameEngine()
    {
        _paint = new Paint();
        _main_character = new MainCharacter();
    }

    Paint _paint;

    public void Update()
    {
        RunGame();
    }

    private void RunGame()
    {
        synchronized (_sync)
        {

        }
    }

    public void Draw(Canvas canvas)
    {
        DrawMainCharacter(canvas);
    }



    private void DrawMainCharacter(Canvas canvas)
    {
        Bitmap main_character = BitmapBank.GetBitmap( AppConstants.GetBitmapsBank().GetMainCharacter());

        Rect rect = _main_character.GetRect(AppConstants.SCREEN_WIDTH, AppConstants.SCREEN_HEIGHT, main_character);
        canvas.drawBitmap(main_character, null, rect, _paint);
    }






    public void SetLastTouch(float x, float y)
    {
        _lastTouchedX = x;
        _lastTouchedY = y;
    }
}
