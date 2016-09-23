package com.a256devs.fastfinger;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.a256devs.fastfinger.Dialogs.DialogMassage;

import java.util.Random;

import static com.a256devs.fastfinger.UiMethods.randomStringFromArray;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener, View.OnClickListener {

    DialogMassage dialog = new DialogMassage();

    int mCounter = 0;
    int mBonusScore;
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


    TextView[] mTargetsText = new TextView[20];
    ImageView[] mTargetsImage = new ImageView[20];

    CountDownTimer mTimer;

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

        mTargetsText[0] = (TextView) findViewById(R.id.target_timer0);
        mTargetsText[1] = (TextView) findViewById(R.id.target_timer1);
        mTargetsText[2] = (TextView) findViewById(R.id.target_timer2);
        mTargetsText[3] = (TextView) findViewById(R.id.target_timer3);
        mTargetsText[4] = (TextView) findViewById(R.id.target_timer4);
        mTargetsText[5] = (TextView) findViewById(R.id.target_timer5);
        mTargetsText[6] = (TextView) findViewById(R.id.target_timer6);
        mTargetsText[7] = (TextView) findViewById(R.id.target_timer7);
        mTargetsText[8] = (TextView) findViewById(R.id.target_timer8);
        mTargetsText[9] = (TextView) findViewById(R.id.target_timer9);
        mTargetsText[10] = (TextView) findViewById(R.id.target_timer10);
        mTargetsText[11] = (TextView) findViewById(R.id.target_timer11);
        mTargetsText[12] = (TextView) findViewById(R.id.target_timer12);
        mTargetsText[13] = (TextView) findViewById(R.id.target_timer13);
        mTargetsText[14] = (TextView) findViewById(R.id.target_timer14);
        mTargetsText[15] = (TextView) findViewById(R.id.target_timer15);
        mTargetsText[16] = (TextView) findViewById(R.id.target_timer16);
        mTargetsText[17] = (TextView) findViewById(R.id.target_timer17);
        mTargetsText[18] = (TextView) findViewById(R.id.target_timer18);
        mTargetsText[19] = (TextView) findViewById(R.id.target_timer19);

        mTargetsImage[0] = (ImageView) findViewById(R.id.target0);
        mTargetsImage[1] = (ImageView) findViewById(R.id.target1);
        mTargetsImage[2] = (ImageView) findViewById(R.id.target2);
        mTargetsImage[3] = (ImageView) findViewById(R.id.target3);
        mTargetsImage[4] = (ImageView) findViewById(R.id.target4);
        mTargetsImage[5] = (ImageView) findViewById(R.id.target5);
        mTargetsImage[6] = (ImageView) findViewById(R.id.target6);
        mTargetsImage[7] = (ImageView) findViewById(R.id.target7);
        mTargetsImage[8] = (ImageView) findViewById(R.id.target8);
        mTargetsImage[9] = (ImageView) findViewById(R.id.target9);
        mTargetsImage[10] = (ImageView) findViewById(R.id.target10);
        mTargetsImage[11] = (ImageView) findViewById(R.id.target11);
        mTargetsImage[12] = (ImageView) findViewById(R.id.target12);
        mTargetsImage[13] = (ImageView) findViewById(R.id.target13);
        mTargetsImage[14] = (ImageView) findViewById(R.id.target14);
        mTargetsImage[15] = (ImageView) findViewById(R.id.target15);
        mTargetsImage[16] = (ImageView) findViewById(R.id.target16);
        mTargetsImage[17] = (ImageView) findViewById(R.id.target17);
        mTargetsImage[18] = (ImageView) findViewById(R.id.target18);
        mTargetsImage[19] = (ImageView) findViewById(R.id.target19);


        for (TextView target : mTargetsText) {
            target.setOnTouchListener(this);
        }

        mTimerText.setOnClickListener(this);
        mVolumeButton.setOnClickListener(this);
        mFacebookButton.setOnClickListener(this);
        mResetButton.setOnClickListener(this);
        mRate.setOnClickListener(this);


        mTimer = new CountDownTimer(1000, 100) {
            @Override
            public void onTick(long l) {
                mBonusScore = (int) (long) l / 100;
                mTargetsText[mIndexOfButton].setText(String.valueOf(mBonusScore));

            }
            @Override
            public void onFinish() {
            }

        };
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
                mTimer.start();
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
                mTimer.cancel();
                invisible(mIndexOfButton);
                mCounter = (mCounter++) + mBonusScore;
                displayCounter(mCounter);
                visible();
                mTimer.start();


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
        for (TextView target : mTargetsText) {
            target.setVisibility(View.INVISIBLE);
        }
        for (ImageView targetImage : mTargetsImage) {
            targetImage.setVisibility(View.INVISIBLE);
        }
    }

    public void invisible(int index) {
        mTargetsText[index].setVisibility(View.INVISIBLE);
        mTargetsImage[index].setVisibility(View.INVISIBLE);
    }

    public void visible() {
        mIndexOfButton = rand.nextInt(mTargetsText.length);
        mTargetsText[mIndexOfButton].setVisibility(View.VISIBLE);
        mTargetsImage[mIndexOfButton].setVisibility(View.VISIBLE);
    }

    public void reset() {
        editor.putInt(getString(R.string.best_score), 0);
        editor.commit();
        readAndShowBestScore();
    }
}
