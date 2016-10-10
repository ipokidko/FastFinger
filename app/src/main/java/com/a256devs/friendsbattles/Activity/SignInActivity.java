package com.a256devs.friendsbattles.Activity;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

import com.a256devs.friendsbattles.R;

public class SignInActivity extends AppCompatActivity implements View.OnClickListener {

    Button signInButton;
    Button signUpButton;
    Button skipButton;
    EditText userName;
    EditText userPassword;

    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;

    private static final String TAG = "QuizActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(TAG, "SignInActivity onCreate() called");
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        setContentView(R.layout.activity_sign_in);

        signInButton = (Button) findViewById(R.id.sign_in_button);
        signUpButton = (Button) findViewById(R.id.sign_up_button);
        skipButton = (Button) findViewById(R.id.skip_button);
        userName = (EditText) findViewById(R.id.user_name);
        userPassword = (EditText) findViewById(R.id.password);
        signInButton.setOnClickListener(this);
        signUpButton.setOnClickListener(this);
        skipButton.setOnClickListener(this);

        sharedPref = getSharedPreferences(getString(R.string.app_preferences), Context.MODE_PRIVATE);
        editor = sharedPref.edit();


    }


    @Override
    public void onClick(View view) {


        switch (view.getId()) {
            case R.id.sign_in_button:
                Log.v(TAG, "SignInActivity in sign in");
                ParseUser.logInInBackground(userName.getText().toString(), userPassword.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser parseUser, ParseException e) {
                        if (parseUser != null) {
                            Log.v(TAG, "SignInActivity sign in work");
                            writeUserToSharedPref(parseUser.getUsername());
                            Log.v(TAG, "SignInActivity write user name successful");
                            //Login Successful
                            //you can display sth or do sth
                            //For example Welcome + ParseUser.getUsername()

                            // if SignIn successful go to MainActivity
                            Intent MainActIntent = new Intent(SignInActivity.this, MainActivity.class);
                            MainActIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(MainActIntent);

                        } else {
                            Log.v(TAG, "SignInActivity signIn fail" + e.getMessage());
                            //Login Fail
                            //get error by calling e.getMessage()
                        }
                    }
                });
                break;
            case R.id.sign_up_button:
                Intent signUpIntent = new Intent(SignInActivity.this, SignUpActivity.class);
                startActivity(signUpIntent);
                break;
            case R.id.skip_button:
                Intent MainActIntent = new Intent(SignInActivity.this, MainActivity.class);
                MainActIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(MainActIntent);
                break;
        }
    }

    public void writeUserToSharedPref(String s) {
        editor.putString("username", s);
        editor.commit();
    }


    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "SignInActivity onStart() called");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "SignInActivity onPause() called");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "SignInActivity onResume() called");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "SignInActivity onStop() called");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "SignInActivity onDestroy() called");
    }


}
