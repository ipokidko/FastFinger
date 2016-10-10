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
import com.parse.ParseException;
import com.parse.ParseUser;

import com.a256devs.friendsbattles.R;
import com.parse.SignUpCallback;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    Button createUserButton;
    Button backToLoginButton;

    EditText mUserName;
    EditText mUserPassword;
    EditText mUserEmail;

    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;

    private static final String TAG = "QuizActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(TAG, "SignUpActivity onCreate() called");
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        setContentView(R.layout.activity_sign_up);

        createUserButton = (Button) findViewById(R.id.create_user_button);
        backToLoginButton = (Button) findViewById(R.id.back_to_login_button);
        mUserName = (EditText) findViewById(R.id.user_name_sign_up);
        mUserPassword = (EditText) findViewById(R.id.password_sign_up);
        mUserEmail = (EditText) findViewById(R.id.email_sign_up);
        createUserButton.setOnClickListener(this);
        backToLoginButton.setOnClickListener(this);

        sharedPref = getSharedPreferences(getString(R.string.app_preferences), Context.MODE_PRIVATE);
        editor = sharedPref.edit();

    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.create_user_button:
                Log.v(TAG, "SignUpActivity in create user");
                ParseUser user = new ParseUser();
                user.setUsername(mUserName.getText().toString());
                user.setPassword(mUserPassword.getText().toString());
                user.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            Log.v(TAG, "SignUpActivity create done");
                            writeUserToSharedPref(getResources().getString(R.string.username), mUserName.getText().toString());
                            writeUserToSharedPref(getResources().getString(R.string.password), mUserPassword.getText().toString());
                            //Register Successful
                            //you can display sth or do sth

                            Intent MainActIntent = new Intent(SignUpActivity.this, MainActivity.class);
                            MainActIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(MainActIntent);

                        } else {
                            Log.v(TAG, "SignUpActivity sign up fail");
                            Log.v(TAG, e.getMessage());
                            //Register Fail
                            //get error by calling e.getMessage()
                        }
                    }
                });
                break;
            case R.id.back_to_login_button:
                Intent signInActivity = new Intent(this, SignInActivity.class);
                startActivity(signInActivity);
                finish();
                break;
        }
    }

    public void writeUserToSharedPref(String tag, String mesaguage) {
        editor.putString(tag, mesaguage);
        editor.commit();
    }



    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "SignUpActivity onStart() called");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "SignUpActivity onPause() called");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "SignUpActivity onResume() called");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "SignUpActivity onStop() called");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "SignUpActivity onDestroy() called");
    }


}
