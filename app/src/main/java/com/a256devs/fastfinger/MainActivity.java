package com.a256devs.fastfinger;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int counter = 0;
    private TextView mScoreInfoTexyView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mScoreInfoTexyView = (TextView) findViewById(R.id.score);
    }


    public void increaseCounter(View view) {
        counter++;
        displayCounter(counter);
    }

    public void startButton(View viev) {
        counter = 0;
        displayCounter(counter);
    }

    public void displayCounter(int counter) {
        mScoreInfoTexyView.setText(String.valueOf(counter));
    }

}
