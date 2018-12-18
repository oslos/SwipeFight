package com.example.oslos.swipefight;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import com.example.oslos.swipefight.objects.EnemyCharacter;
import com.example.oslos.swipefight.objects.MainCharacter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameEngine
{
    static List<EnemyCharacter> _enemy_characters = new ArrayList<EnemyCharacter>();
    static MainCharacter _main_character;
    static final Object _sync = new Object();
    static float _lastTouchedX, _lastTouchedY;
    static int score;
    static long lastSpawn;
    static int difficulty;

    public GameEngine()
    {
        _paint = new Paint();
        _main_character = new MainCharacter();
        _enemy_characters.add(new EnemyCharacter(1));
        _enemy_characters.add(new EnemyCharacter(2));
        _enemy_characters.add(new EnemyCharacter(3));
        _enemy_characters.add(new EnemyCharacter(4));
        score = 0;
        lastSpawn = 0;
        difficulty = 20;
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
            Random rnd = new Random();
            if((System.currentTimeMillis() - lastSpawn)>(10000 / difficulty)) {
                _enemy_characters.get(rnd. nextInt(4)).SetToSpawn();
                lastSpawn =System.currentTimeMillis();
            }
        }
    }

    public void Draw(Canvas canvas)
    {
        DrawMainCharacter(canvas);
        DrawEnemyCharacters(canvas);
    }



    private void DrawMainCharacter(Canvas canvas)
    {
        Bitmap main_character = BitmapBank.GetBitmap( AppConstants.GetBitmapsBank().GetCharacter());

        Rect rect = _main_character.GetRect(AppConstants.SCREEN_WIDTH, AppConstants.SCREEN_HEIGHT, main_character);
        canvas.drawBitmap(main_character, null, rect, _paint);
    }

    private void DrawEnemyCharacters(Canvas canvas)
    {
        Bitmap enemy_character = BitmapBank.GetBitmap( AppConstants.GetBitmapsBank().GetCharacter());

        for (EnemyCharacter eneChar:_enemy_characters
             ) {
            if(eneChar.IsAlive()) {
                Rect rect = eneChar.GetRect(AppConstants.SCREEN_WIDTH, AppConstants.SCREEN_HEIGHT, enemy_character);
                canvas.drawBitmap(enemy_character, null, rect, _paint);
            }
        }

    }

    public void Kill(int location)
    {
        if(_enemy_characters.get(location).IsAlive())
        {
            score++;
            _enemy_characters.get(location).Kill();
        }
    }

/*
    public void SetLastTouch(float x, float y)
    {
        _lastTouchedX = x;
        _lastTouchedY = y;
    }*/
}
