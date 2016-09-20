package com.a256devs.fastfinger;


import java.util.Random;

public class UiMethods {
    public static String randomStringFromArray (String[] array) {
        Random rand = new Random();
        return array[rand.nextInt(array.length)];
    }
}
