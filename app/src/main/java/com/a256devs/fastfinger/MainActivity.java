package com.a256devs.fastfinger;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        TextView count = (TextView) findViewById(R.id.score);
        count.setText(String.valueOf(counter));
    }

}
