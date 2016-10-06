package com.a256devs.friendsbattles.Activity;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseUser;

import com.a256devs.friendsbattles.R;

public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        setContentView(R.layout.activity_login);

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("AVB7hjOJRmwVjDr9yOByTpRYfJZkIDRsdJFGCquS")
                .clientKey("3yxExy7P6xuwbt79uSCuYu09a4dZlrNp56IFLz6W")
                .server("https://parseapi.back4app.com/").build()
        );
        
    }
}
