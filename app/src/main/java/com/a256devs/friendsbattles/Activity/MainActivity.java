package com.a256devs.friendsbattles.Activity;

import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.media.AudioManager;
import android.media.SoundPool;
import android.net.Uri;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.a256devs.friendsbattles.Dialogs.FacebookDialog;
import com.a256devs.friendsbattles.Dialogs.HelpDialog;
import com.a256devs.friendsbattles.Dialogs.ResetScoreDialog;
import com.a256devs.friendsbattles.Dialogs.SettingsDialog;
import com.a256devs.friendsbattles.Dialogs.ShareDialog;
import com.a256devs.friendsbattles.R;

import java.util.Random;


import static com.a256devs.friendsbattles.Utils.UiMethods.randomStringFromArray;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener, View.OnClickListener, ShareDialog.ShareYesListener, FacebookDialog.FacebookYesListener, ResetScoreDialog.ResetScoreYesListener {

    FacebookDialog facebookDialog = new FacebookDialog();
    ResetScoreDialog resetScoreDialog = new ResetScoreDialog();
    ShareDialog shareDialog = new ShareDialog();
    HelpDialog helpDialog = new HelpDialog();
    SettingsDialog settingsDialog = new SettingsDialog();
    private static final String TAG = "QuizActivity";


    int mCounter = 0;
    int mBonusScore;
    private TextView mScoreInfoTextView;
    private TextView mTimerText;
    private TextView mBestScore;
    private TextView mLoginText;
    private boolean mVolumeState = true;
    private ImageView mVolumeButton;
    private ImageView mFacebookButton;
    private ImageView mResetButton;
    private ImageView mRateButton;
    private ImageView mHelpButton;
    private ImageView mSettingsButton;
    private boolean mIsGame = false;

    public int color = Color.RED;

    Intent intent = new Intent(Intent.ACTION_VIEW);

    Random rand = new Random();
    private int mIndexOfButton;


    TextView[] mTargetsText = new TextView[20];

    CountDownTimer mTimer;

    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;
    private int mHighScore;

    private SoundPool mSoundPool;
    private int mSoundId = 1;
    AudioManager audioManager;
    float curVolume;
    float maxVolume;
    float leftVolume;
    float rightVolume;
    int priority;
    int no_loop;
    float normal_playback_rate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // fullscreen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        setContentView(R.layout.activity_main);


        mSoundPool = new SoundPool(4, AudioManager.STREAM_MUSIC, 100);
        mSoundPool.load(this, R.raw.base, 1);
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        curVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        leftVolume = curVolume / maxVolume;
        rightVolume = curVolume / maxVolume;
        priority = 1;
        no_loop = 0;
        normal_playback_rate = 1f;


        mScoreInfoTextView = (TextView) findViewById(R.id.scoreNumber);
        mTimerText = (TextView) findViewById(R.id.timer);
        mBestScore = (TextView) findViewById(R.id.bestScoreNumber);
        mVolumeButton = (ImageView) findViewById(R.id.soundButton);
        mFacebookButton = (ImageView) findViewById(R.id.facebook_button);
        mResetButton = (ImageView) findViewById(R.id.reset_button);
        mRateButton = (ImageView) findViewById(R.id.rate_button);
        mHelpButton = (ImageView) findViewById(R.id.help_button);
        mSettingsButton = (ImageView) findViewById(R.id.settings_button);
        mLoginText = (TextView) findViewById(R.id.login_tv);

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


        for (TextView target : mTargetsText) {
            target.setOnTouchListener(this);
        }

        readAndSetColor();

        mTimerText.setOnClickListener(this);
        mVolumeButton.setOnClickListener(this);
        mFacebookButton.setOnClickListener(this);
        mResetButton.setOnClickListener(this);
        mRateButton.setOnClickListener(this);
        mHelpButton.setOnClickListener(this);
        mSettingsButton.setOnClickListener(this);
        mLoginText.setOnClickListener(this);


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
                if (mVolumeState)
                    mSoundPool.play(mSoundId, leftVolume, rightVolume, priority, no_loop, normal_playback_rate);
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
                setSoundEffectsOfButtons(mVolumeState);
                break;
            case R.id.facebook_button:
                if (!facebookDialog.isAdded()) {
                    facebookDialog.show(getFragmentManager(), "facebook");
                }
                break;
            case R.id.reset_button:
                if (!resetScoreDialog.isAdded()) {
                    resetScoreDialog.show(getFragmentManager(), "resetScore");
                }
                break;
            case R.id.rate_button:
                if (!shareDialog.isAdded()) {
                    shareDialog.show(getFragmentManager(), "rate app");
                }
                break;
            case R.id.help_button:
                if (!helpDialog.isAdded()) {
                    helpDialog.show(getFragmentManager(), "help app");
                }
                break;
            case R.id.settings_button:
                if (!settingsDialog.isAdded()) {
                    settingsDialog.show(getFragmentManager(), "settings app");
                }
                break;
            case R.id.login_tv:
                Intent signInIntent = new Intent(MainActivity.this, SignInActivity.class);
                startActivity(signInIntent);
                break;
        }

    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
            if (mIsGame) {
                if (mVolumeState)
                    mSoundPool.play(mSoundId, leftVolume, rightVolume, priority, no_loop, normal_playback_rate);
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

    public void writeColor(int selectedColor) {
        editor.putInt(getString(R.string.color_state), selectedColor);
        editor.commit();
    }

    public void readAndSetColor() {
        color = sharedPref.getInt(getString(R.string.color_state), color);

        for (TextView target : mTargetsText) {
            GradientDrawable currentBackground = new GradientDrawable();
            currentBackground.setShape(GradientDrawable.OVAL);
            currentBackground.setStroke(7, color);
            currentBackground.setColor(Color.WHITE);
            target.setBackground(currentBackground);
            target.setTextColor(color);
        }
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
        setSoundEffectsOfButtons(mVolumeState);
        readAndShowBestScore();

        setUserName();

        TextView tipsTV = (TextView) findViewById(R.id.main_tips_tv);
        String s = randomStringFromArray(getResources().getStringArray(R.array.tips));
        tipsTV.setText(s);

    }

    public void invisibleAll() {
        for (TextView target : mTargetsText) {
            target.setVisibility(View.INVISIBLE);
        }
    }

    public void invisible(int index) {
        mTargetsText[index].setVisibility(View.INVISIBLE);
    }

    public void visible() {
        mIndexOfButton = rand.nextInt(mTargetsText.length);
        mTargetsText[mIndexOfButton].setVisibility(View.VISIBLE);
    }


    public void setSoundEffectsOfButtons(boolean state) {
        mFacebookButton.setSoundEffectsEnabled(state);
        mResetButton.setSoundEffectsEnabled(state);
        mTimerText.setSoundEffectsEnabled(state);
        mRateButton.setSoundEffectsEnabled(state);
    }

    @Override
    public void onFacebookPositiveClick(DialogFragment dialog) {

    }

    @Override
    public void onResetScorePositiveClick(DialogFragment dialog) {
        editor.putInt(getString(R.string.best_score), 0);
        editor.commit();
        readAndShowBestScore();
    }

    @Override
    public void onSharePositiveClick(DialogFragment dialog) {
        intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=com.a256devs.friendsbattles"));
        startActivity(intent);
    }


    public void setUserName() {
        String s = sharedPref.getString("username", "name");
        mLoginText.setText(s);
        Log.v(TAG, "set user name MainAct");
    }

}


