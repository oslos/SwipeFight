package com.example.oslos.swipefight;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;

import com.example.oslos.swipefight.objects.EnemyCharacter;
import com.example.oslos.swipefight.objects.MainCharacter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static android.content.Context.MODE_PRIVATE;
import static com.example.oslos.swipefight.AppConstants.gameActivity;

public class GameEngine
{
    static List<EnemyCharacter> _enemy_characters = new ArrayList<EnemyCharacter>();
    static MainCharacter _main_character;
    static final Object _sync = new Object();
    static float _lastTouchedX, _lastTouchedY;
    static int score;
    static long lastSpawn;
    static int difficulty;
    static SharedPreferences sharedPreferences;
    static Object vibrationService;

    public GameEngine(SharedPreferences sharedPreferences,Object vibrationService)
    {
        this.sharedPreferences = sharedPreferences;
        _paint = new Paint();
        _main_character = new MainCharacter();
        _enemy_characters.add(new EnemyCharacter(1));
        _enemy_characters.add(new EnemyCharacter(2));
        _enemy_characters.add(new EnemyCharacter(3));
        _enemy_characters.add(new EnemyCharacter(4));
        score = 0;
        lastSpawn = 0;
        difficulty = 20;
        this.vibrationService = vibrationService;

    }

    Paint _paint;

    public void Update()
    {
        RunGame();
    }

    public void Reset(){

        _enemy_characters.set(0,new EnemyCharacter(1));
        _enemy_characters.set(1,new EnemyCharacter(2));
        _enemy_characters.set(2,new EnemyCharacter(3));
        _enemy_characters.set(3,new EnemyCharacter(4));
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
            for (EnemyCharacter enChar: _enemy_characters
                 ) {
                if(enChar.IsAlive() && (System.currentTimeMillis() - enChar.GetTimeAlive())>(40000 / difficulty) && score!=0 )
                {
                    GameOver();
                }
            }
        }
    }

    public void GameOver()
    {
        if(score> sharedPreferences.getInt("highscore",0)) {
            SharedPreferences.Editor editor = sharedPreferences.edit();//getSharedPreferences("Prefs", MODE_PRIVATE).edit();
            editor.putInt("highscore", score);
            editor.apply();
        }


        Vibrator v = (Vibrator) vibrationService;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            v.vibrate(VibrationEffect. createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
        } else {
            v.vibrate(500);

        }
        Reset();
        Intent returnIntent = new Intent();
        returnIntent.putExtra("score",score);
        gameActivity.setResult(Activity.RESULT_OK,returnIntent);
        score = 0;
        gameActivity.finish();
    }

    public void Draw(Canvas canvas)
    {
        DrawMainCharacter(canvas);
        DrawEnemyCharacters(canvas);
        DrawScore(canvas);
        DrawHighScore(canvas);
    }

    private void DrawHighScore(Canvas canvas)
    {
        float textSize = 50;

        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setTextSize(textSize);
        paint.setColor(Color.argb(255,0,0,0));
        paint.setTextAlign(Paint.Align.LEFT);
        float baseline = -paint.ascent() * 2; // ascent() is negative
        int width = (int) (paint.measureText("HighScore: " + String.valueOf(sharedPreferences.getInt("highscore",0))) + 0.5f); // round
        int height = (int) (baseline + paint.descent() + 0.5f);
        Bitmap image = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        canvas.drawText("HighScore: " + String.valueOf(sharedPreferences.getInt("highscore",0)), 0, baseline, paint);

    }

    private void DrawScore(Canvas canvas)
    {

        float textSize = 50;

        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setTextSize(textSize);
        paint.setColor(Color.argb(255,0,0,0));
        paint.setTextAlign(Paint.Align.LEFT);
        float baseline = -paint.ascent(); // ascent() is negative
        int width = (int) (paint.measureText("Score: " + String.valueOf(score)) + 0.5f); // round
        int height = (int) (baseline + paint.descent() + 0.5f);
        Bitmap image = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        canvas.drawText("Score: " + String.valueOf(score), 0, baseline, paint);

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

}
