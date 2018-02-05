package com.ksug.speedmatch.utility;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.ksug.speedmatch.R;
import com.ksug.speedmatch.constant.ApplicationConstant;
import com.ksug.speedmatch.manager.AnalyticsManager;


public class DialogUtil {

    public static void showAboutMessage(final Context context, final FirebaseAnalytics mFirebaseAnalytics) {
        final AlertDialog alertDialog = new AlertDialog.Builder(context)
                .setPositiveButton(context.getResources().getString(R.string.cta_rate_us),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                launchPlayStore(context);
                                dialog.dismiss();
                            }
                        })
                .setNegativeButton(context.getResources().getString(R.string.cta_share),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                share(context, mFirebaseAnalytics);
                                dialog.dismiss();
                            }
                        }).create();
        alertDialog.setTitle(R.string.app_name);
        alertDialog.setMessage(context.getResources().getString(R.string.copyright_message));
        alertDialog.show();
    }

    private static void launchPlayStore(Context context) {
        Uri uri = Uri.parse("market://details?id=" + ApplicationConstant.APP_ID);
        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
        try {
            context.startActivity(goToMarket);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void share(Context context, FirebaseAnalytics mFirebaseAnalytics) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, context.getString(R.string.share_via_subject));
        intent.putExtra(Intent.EXTRA_TEXT, context.getString(R.string.share_via_text));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(Intent.createChooser(intent, context.getString(R.string.share_via_message)));
        AnalyticsManager.trackShares(mFirebaseAnalytics);
    }
}