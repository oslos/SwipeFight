package com.example.oslos.swipefight;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Shader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class StartScreen extends Activity implements View.OnClickListener {

    TextView score;
    TextView highscore;
    TextView gameover;
    Button start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);
        start = (Button) findViewById(R.id.start);
        score = (TextView) findViewById(R.id.score);
        highscore = (TextView) findViewById(R.id.highscore);
        gameover = (TextView) findViewById(R.id.gameover);
        gameover.setText("SWIPE FIGHT");
        score.setText(" ");
        SharedPreferences sharedPreferences = getSharedPreferences("prefs",MODE_PRIVATE);
        highscore.setText("HighScore: " + String.valueOf(sharedPreferences.getInt("highscore",0)));
        start.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Intent i = new Intent(this, GameActivity.class);

        startActivityForResult(i, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        gameover.setText("GAME OVER!");
        score.setText("Score: " + String .valueOf(data.getIntExtra("score",0)));
        SharedPreferences sharedPreferences = getSharedPreferences("prefs",MODE_PRIVATE);
        highscore.setText("HighScore: " + String.valueOf(sharedPreferences.getInt("highscore",0)));

    }
}