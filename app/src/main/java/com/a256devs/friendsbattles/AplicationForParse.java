package com.a256devs.friendsbattles;

import com.parse.Parse;

/**
 * Created by user on 07.10.2016.
 */

public class AplicationForParse extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("AVB7hjOJRmwVjDr9yOByTpRYfJZkIDRsdJFGCquS")
                .clientKey("3yxExy7P6xuwbt79uSCuYu09a4dZlrNp56IFLz6W")
                .server("https://parseapi.back4app.com/").build()
        );
    }
}
