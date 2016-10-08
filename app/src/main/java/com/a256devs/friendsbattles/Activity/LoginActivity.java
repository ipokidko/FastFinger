package com.a256devs.friendsbattles.Activity;


import android.content.Context;
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
import com.parse.SignUpCallback;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    Button signInButton;
    Button signUpButton;
    Button skipButton;
    EditText userName;
    EditText userPassword;
    EditText userEmail;

    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;

    private static final String TAG = "QuizActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(TAG, "onCreate() called");
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        setContentView(R.layout.activity_login);

        signInButton = (Button) findViewById(R.id.sign_in_button);
        signUpButton = (Button) findViewById(R.id.sign_up_button);
        skipButton = (Button) findViewById(R.id.skip_button);
        userName = (EditText) findViewById(R.id.user_name);
        userPassword = (EditText) findViewById(R.id.password);
        userEmail = (EditText) findViewById(R.id.email);
        signInButton.setOnClickListener(this);
        signUpButton.setOnClickListener(this);

        sharedPref = getSharedPreferences(getString(R.string.app_preferences), Context.MODE_PRIVATE);
        editor = sharedPref.edit();


    }


    @Override
    public void onClick(View view) {


        switch (view.getId()) {
            case R.id.sign_in_button:
                Log.v(TAG, "in sign in");
                ParseUser.logInInBackground(userName.getText().toString(), userPassword.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser parseUser, ParseException e) {
                        if (parseUser != null) {
                            Log.v(TAG, "sign in work");
                            writeUserToSharedPref(parseUser.getUsername());
                            Log.v(TAG, "set user name");
                            //Login Successful
                            //you can display sth or do sth
                            //For example Welcome + ParseUser.getUsername()

                        } else {
                            Log.v(TAG, "signIn fail");
                            //Login Fail
                            //get error by calling e.getMessage()
                        }
                    }
                });
                break;
            case R.id.sign_up_button:
                Log.v(TAG, "in sign up");
                ParseUser user = new ParseUser();
                user.setUsername(userName.getText().toString());
                user.setPassword(userPassword.getText().toString());
                user.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            Log.v("Flow", "sign up done");
                            writeUserToSharedPref(userName.getText().toString());
                            //Register Successful
                            //you can display sth or do sth
                        } else {
                            Log.v("Flow", "sign up fail");
                            Log.v("Flow", e.getMessage());
                            //Register Fail
                            //get error by calling e.getMessage()
                        }
                    }
                });
                break;
            case R.id.skip_button:

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
        Log.d(TAG, "onStart() called");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }


}
