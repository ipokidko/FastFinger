package com.a256devs.fastfinger;

import android.content.Context;
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

    ImageButton button1;
    ImageButton button2;
    ImageButton button3;
    ImageButton button4;
    ImageButton button5;
    ImageButton button6;
    ImageButton button7;
    ImageButton button8;
    ImageButton button9;
    ImageButton button10;
    ImageButton button11;
    ImageButton button12;
    ImageButton button13;
    ImageButton button14;
    ImageButton button15;
    ImageButton button16;
    ImageButton button17;
    ImageButton button18;
    ImageButton button19;
    ImageButton button20;
    ImageButton button21;
    ImageButton button22;
    ImageButton button23;
    ImageButton button24;

    Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        setContentView(R.layout.activity_main);
        mScoreInfoTexyView = (TextView) findViewById(R.id.score);

        button1 = (ImageButton) findViewById(R.id.imageView2);
        button2 = (ImageButton) findViewById(R.id.imageView3);
        button3 = (ImageButton) findViewById(R.id.imageView4);
        button4 = (ImageButton) findViewById(R.id.imageView5);
        button5 = (ImageButton) findViewById(R.id.imageView6);
        button6 = (ImageButton) findViewById(R.id.imageView7);
        button7 = (ImageButton) findViewById(R.id.imageView8);
        button8 = (ImageButton) findViewById(R.id.imageView9);
        button9 = (ImageButton) findViewById(R.id.imageView10);
        button10 = (ImageButton) findViewById(R.id.imageView11);
        button11 = (ImageButton) findViewById(R.id.imageView12);
        button12 = (ImageButton) findViewById(R.id.imageView13);
        button13 = (ImageButton) findViewById(R.id.imageView14);
        button14 = (ImageButton) findViewById(R.id.imageView15);
        button15 = (ImageButton) findViewById(R.id.imageView16);
        button16 = (ImageButton) findViewById(R.id.imageView17);
        button17 = (ImageButton) findViewById(R.id.imageView18);
        button18 = (ImageButton) findViewById(R.id.imageView19);
        button19 = (ImageButton) findViewById(R.id.imageView20);
        button20 = (ImageButton) findViewById(R.id.imageView21);
        button21 = (ImageButton) findViewById(R.id.imageView22);
        button22 = (ImageButton) findViewById(R.id.imageView23);
        button23 = (ImageButton) findViewById(R.id.imageView24);
        button24 = (ImageButton) findViewById(R.id.imageView25);

        startButton = (Button) findViewById(R.id.button);


        button1.setOnTouchListener(this);
        button2.setOnTouchListener(this);
        button3.setOnTouchListener(this);
        button4.setOnTouchListener(this);
        button5.setOnTouchListener(this);
        button6.setOnTouchListener(this);
        button7.setOnTouchListener(this);
        button8.setOnTouchListener(this);
        button9.setOnTouchListener(this);
        button10.setOnTouchListener(this);
        button11.setOnTouchListener(this);
        button12.setOnTouchListener(this);
        button13.setOnTouchListener(this);
        button14.setOnTouchListener(this);
        button15.setOnTouchListener(this);
        button16.setOnTouchListener(this);
        button17.setOnTouchListener(this);
        button18.setOnTouchListener(this);
        button19.setOnTouchListener(this);
        button20.setOnTouchListener(this);
        button21.setOnTouchListener(this);
        button22.setOnTouchListener(this);
        button23.setOnTouchListener(this);
        button24.setOnTouchListener(this);

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
}
