package com.ksug.speedmatch.constant;

import com.ksug.speedmatch.R;

public abstract class ApplicationConstant {

    public static final String APP_ID = "com.ksug.speedmatch";

    public static final long GAME_PLAY_TIME = 180 * 1000;
    public static final int COUNT_DOWN_TIMER = 2;
    public static final long MILLI_SECOND = 1000;
    public static final long FADE_OUT_DURATION = 500;
    public static final long FADE_IN_DURATION = 200;

    public static final int BG_SAME = R.drawable.normal_circle;
    public static final int BG_DIFFERENT = R.drawable.different_circle;
    public static final String QUESTION_SET_A = "A";
    public static final String QUESTION_SET_B = "B";
    public static final String QUESTION_SET_C = "C";

    public static final int BONUS_SPLIT = 5;

    public static final String SP_HIGH_SCORE = APP_ID + ".score";
    public static final String SP_MAXIMUM_CARDS = APP_ID + ".cards";
    public static final String EXTRA_GAME_SCORE = "GAME_SCORE";
}