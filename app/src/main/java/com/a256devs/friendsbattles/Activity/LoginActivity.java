package com.a256devs.friendsbattles.Activity;


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
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v("flow", "Start of OnCreate");

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


        Log.v("flow", "end of OnCreate");

    }


    @Override
    public void onClick(View view) {
        Log.v("Flow", "in on click");

        switch (view.getId()) {
            case R.id.sign_in_button:
                Log.v("Flow", "in sign in");
                ParseUser.logInInBackground("Username", "Password", new LogInCallback() {
                    @Override
                    public void done(ParseUser parseUser, ParseException e) {
                        if (parseUser != null) {
                            //Login Successful
                            //you can display sth or do sth
                            //For example Welcome + ParseUser.getUsername()

                        } else {
                            //Login Fail
                            //get error by calling e.getMessage()
                        }
                    }
                });
                break;
            case R.id.sign_up_button:
                Log.v("flow", "in sign up");
                ParseUser user = new ParseUser();
                user.setUsername(userName.getText().toString());
                user.setPassword(userPassword.getText().toString());
                user.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            Log.v("Flow", "sign up done");

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


}
