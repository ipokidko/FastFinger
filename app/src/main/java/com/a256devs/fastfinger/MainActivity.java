package com.a256devs.fastfinger;

import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.a256devs.fastfinger.Dialogs.DialogMassage;

import java.util.Random;

import static com.a256devs.fastfinger.UiMethods.randomStringFromArray;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener, View.OnClickListener {

    DialogMassage dialog = new DialogMassage();

    int mCounter = 0;
    private TextView mScoreInfoTextView;
    private TextView mTimerText;
    private TextView mBestScore;
    private boolean mVolumeState = true;
    private ImageView mVolumeButton;
    private ImageView mFacebookButton;
    private ImageView mResetButton;
    private ImageView mRate;
    private boolean mIsGame = false;

    Intent intent = new Intent(Intent.ACTION_VIEW);

    Random rand = new Random();
    private int mIndexOfButton;


    ImageButton[] buttons = new ImageButton[24];

    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;
    private int mHighScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // fullscreen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        setContentView(R.layout.activity_main);

        mScoreInfoTextView = (TextView) findViewById(R.id.scoreNumber);
        mTimerText = (TextView) findViewById(R.id.timer);
        mBestScore = (TextView) findViewById(R.id.bestScoreNumber);
        mVolumeButton = (ImageView) findViewById(R.id.soundButton);
        mFacebookButton = (ImageView) findViewById(R.id.facebook_button);
        mResetButton = (ImageView) findViewById(R.id.reset_button);
        mRate = (ImageView) findViewById(R.id.rate_button);

        // Getting SharedPreferences file or read it if exist.
        sharedPref = getSharedPreferences(getString(R.string.app_preferences), Context.MODE_PRIVATE);
        editor = sharedPref.edit();

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

        for (ImageButton button : buttons) {
            button.setOnTouchListener(this);
        }

        mTimerText.setOnClickListener(this);
        mVolumeButton.setOnClickListener(this);
        mFacebookButton.setOnClickListener(this);
        mResetButton.setOnClickListener(this);
        mRate.setOnClickListener(this);


    }

    public void displayCounter(int counter) {
        mScoreInfoTextView.setText(String.valueOf(counter));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.timer:
                mCounter = 0;
                displayCounter(mCounter);
                countDownTimer();
                mTimerText.setClickable(false);
                mIsGame = true;
                invisibleAll();
                visible();
                break;
            case R.id.soundButton:
                mVolumeState = !mVolumeState;
                writeVolumeState();
                readAndSetResourceVolumeState();
                break;
            case R.id.facebook_button:
                dialog.show(getFragmentManager(), "facebook");
                break;
            case R.id.reset_button:
                reset();
                break;
            case R.id.rate_button:
                intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=air.ru.underair.city2048&hl=ru"));
                startActivity(intent);
                break;
        }

    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
            if (mIsGame) {
                mCounter++;
                displayCounter(mCounter);
                invisible(mIndexOfButton);
                visible();

            }
        }
        return false;
    }


    public void countDownTimer() {

        new CountDownTimer(15000, 100) {

            public void onTick(long millisUntilFinished) {
                mTimerText.setText(String.format("%d.%d", millisUntilFinished / 1000, (millisUntilFinished % 1000) / 100));
            }

            public void onFinish() {
                mTimerText.setText("Try again!");
                mTimerText.setClickable(true);
                mIsGame = false;

                invisible(mIndexOfButton);

                if (mCounter > mHighScore) {
                    writeBestStore();
                    readAndShowBestScore();
                }
            }
        }.start();
    }

    public void writeBestStore() {
        editor.putInt(getString(R.string.best_score), mCounter);
        editor.commit();
    }

    public void writeVolumeState() {
        editor.putBoolean(getString(R.string.volume_state), mVolumeState);
        editor.commit();
    }

    public void readAndShowBestScore() {
        mHighScore = sharedPref.getInt(getString(R.string.best_score), mCounter);
        mBestScore.setText(String.valueOf(mHighScore));
    }

    public void readAndSetResourceVolumeState() {
        mVolumeState = sharedPref.getBoolean(getString(R.string.volume_state), mVolumeState);
        if (mVolumeState) {
            mVolumeButton.setImageResource(R.drawable.volume_on);
        } else {
            mVolumeButton.setImageResource(R.drawable.volume_off);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        readAndSetResourceVolumeState();
        readAndShowBestScore();

        TextView tipsTV = (TextView) findViewById(R.id.main_tips_tv);
        String s = randomStringFromArray(getResources().getStringArray(R.array.tips));
        tipsTV.setText(s);

    }

    public void invisibleAll() {
        for (ImageButton button : buttons) {
            button.setVisibility(View.INVISIBLE);
        }
    }

    public void invisible(int index) {
        buttons[index].setVisibility(View.INVISIBLE);
    }

    public void visible() {
        mIndexOfButton = rand.nextInt(buttons.length);
        buttons[mIndexOfButton].setVisibility(View.VISIBLE);
    }

    public void reset() {
        editor.putInt(getString(R.string.best_score), 0);
        editor.commit();
        readAndShowBestScore();
    }
}
