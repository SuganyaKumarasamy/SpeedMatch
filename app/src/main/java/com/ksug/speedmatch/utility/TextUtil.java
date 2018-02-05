package com.ksug.speedmatch.utility;

import java.util.Locale;
import java.util.Random;


public abstract class TextUtil {

    public static String getFormattedScore(long doubleValue) {
        return String.format(Locale.US, "%06d", doubleValue);
    }

    public static String getFormattedTime(long doubleValue) {
        return String.format(Locale.US, "%02d", doubleValue);
    }

    public static int getRandomInt(int min, int max) {
        return new Random().nextInt((max - min) + 1) + min;
    }

}