package com.ksug.speedmatch.manager;

import android.os.Bundle;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.ksug.speedmatch.constant.ApplicationConstant;

public abstract class AnalyticsManager {

    public static void trackScore(FirebaseAnalytics mFirebaseAnalytics, long score) {
        Bundle bundle = new Bundle();
        bundle.putLong(FirebaseAnalytics.Param.SCORE, score);
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.POST_SCORE, bundle);
    }

    public static void trackCards(FirebaseAnalytics mFirebaseAnalytics, long cards) {
        Bundle bundle = new Bundle();
        bundle.putLong(FirebaseAnalytics.Param.SCORE, cards);
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Param.LEVEL, bundle);
    }

    public static void trackShares(FirebaseAnalytics mFirebaseAnalytics) {
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, ApplicationConstant.APP_ID);
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SHARE, bundle);
    }
}
