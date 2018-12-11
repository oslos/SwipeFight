package com.example.oslos.swipefight;

import android.content.Context;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView background;
TextView textView;
TextView running;
Integer number;
Integer direction;

    private Handler handler;

    // Define the code block to be executed
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {

            // Insert custom code here
            //running.setText(number);
            number++;
            // Repeat every 2 seconds
            handler.postDelayed(runnable, 20000);
        }
    };

// Start the Runnable immediately

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        number=0;
        background = findViewById(R.id.imageView);
        textView = findViewById(R.id.DebugText);
        running = findViewById(R.id.Running);
        running.bringToFront();
        textView.bringToFront();
        handler = new Handler();
        background.setOnTouchListener(new OnSwipeTouchListener(MainActivity.this) {
            public void onSwipeTop() {
                Toast.makeText(MainActivity.this, "top", Toast.LENGTH_SHORT).show();
                textView.setText("Top");
            }
            public void onSwipeRight() {
                Toast.makeText(MainActivity.this, "right", Toast.LENGTH_SHORT).show();
                textView.setText("Right");
            }
            public void onSwipeLeft() {
                Toast.makeText(MainActivity.this, "left", Toast.LENGTH_SHORT).show();
                textView.setText("Left");
            }
            public void onSwipeBottom() {
                Toast.makeText(MainActivity.this, "bottom", Toast.LENGTH_SHORT).show();
                textView.setText("Bottom");
            }

        });
        handler.post(runnable);
        //TEST
    }


    @Override
    public void onClick(View view) {
        //if
    }
}
