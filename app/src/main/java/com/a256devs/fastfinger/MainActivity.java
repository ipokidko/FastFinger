package com.a256devs.fastfinger;

import android.content.Context;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener, View.OnClickListener {

    int counter = 0;
    private TextView mScoreInfoTexyView;
    private TextView mTimerText;

    ImageButton[] buttons = new ImageButton[24];

    Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        setContentView(R.layout.activity_main);
        mScoreInfoTexyView = (TextView) findViewById(R.id.score);
        mTimerText = (TextView) findViewById(R.id.timer);

        buttons[0] = (ImageButton) findViewById(R.id.imageView2);
        buttons[1] = (ImageButton) findViewById(R.id.imageView3);
        buttons[2] = (ImageButton) findViewById(R.id.imageView4);
        buttons[3] = (ImageButton) findViewById(R.id.imageView5);
        buttons[4] = (ImageButton) findViewById(R.id.imageView6);
        buttons[5] = (ImageButton) findViewById(R.id.imageView7);
        buttons[6] = (ImageButton) findViewById(R.id.imageView8);
        buttons[7] = (ImageButton) findViewById(R.id.imageView9);
        buttons[8] = (ImageButton) findViewById(R.id.imageView10);
        buttons[9] = (ImageButton) findViewById(R.id.imageView11);
        buttons[10] = (ImageButton) findViewById(R.id.imageView12);
        buttons[11] = (ImageButton) findViewById(R.id.imageView13);
        buttons[12] = (ImageButton) findViewById(R.id.imageView14);
        buttons[13] = (ImageButton) findViewById(R.id.imageView15);
        buttons[14] = (ImageButton) findViewById(R.id.imageView16);
        buttons[15] = (ImageButton) findViewById(R.id.imageView17);
        buttons[16] = (ImageButton) findViewById(R.id.imageView18);
        buttons[17] = (ImageButton) findViewById(R.id.imageView19);
        buttons[18] = (ImageButton) findViewById(R.id.imageView20);
        buttons[19] = (ImageButton) findViewById(R.id.imageView21);
        buttons[20] = (ImageButton) findViewById(R.id.imageView22);
        buttons[21] = (ImageButton) findViewById(R.id.imageView23);
        buttons[22] = (ImageButton) findViewById(R.id.imageView24);
        buttons[23] = (ImageButton) findViewById(R.id.imageView25);

        startButton = (Button) findViewById(R.id.button);

        for (ImageButton button : buttons) {
            button.setOnTouchListener(this);
        }

        startButton.setOnClickListener(this);

    }

    public void displayCounter(int counter) {
        mScoreInfoTexyView.setText(String.valueOf(counter));
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.button) {
            counter = 0;
            displayCounter(counter);
            countDownTimer();
        }

    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
            counter++;
            displayCounter(counter);
        }
        return false;
    }


    public void countDownTimer(){

        new CountDownTimer(15000, 200) {

            public void onTick(long millisUntilFinished) {
                mTimerText.setText(String.format("%d.%d", millisUntilFinished / 1000, (millisUntilFinished % 1000) / 100));
            }

            public void onFinish() {
                mTimerText.setText("done!");
            }
        }.start();
    }
}
