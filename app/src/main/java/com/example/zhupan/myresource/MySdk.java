package com.example.zhupan.myresource;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.Log;

import com.example.zhupan.myresource.config.Constants;

import java.util.Locale;

public class MySdk {
    private static final String TAG = MySdk.class.getSimpleName();
    private static MySdk sdk;

    private MySdk() {
    }

    private static final Object block = new Object();

    public static MySdk getInstant() {
        if (sdk == null) {
            synchronized (block) {
                if (sdk == null) {
                    sdk = new MySdk();
                }
            }
        }
        return sdk;
    }

    public void setLanguage(Context context, String language) {
        updateConfiguration(context, language);
    }
    static Resources resources2;
    private void updateConfiguration(Context context, String language) {
        Resources resources = context.getResources();
        resources2 = context.getResources().getSystem();
        DisplayMetrics dm = resources.getDisplayMetrics();
        Configuration configuration = resources.getConfiguration();
        Locale locale = null;
        switch (language) {
            case Constants.CN:
                Log.i(TAG, "updateConfiguration:cn");
                locale = Locale.ENGLISH;
                break;
            case Constants.EN:
                Log.i(TAG, "updateConfiguration:en ");
                locale = Locale.ENGLISH;
                break;
            case Constants.KR:
                Log.i(TAG, "updateConfiguration:en ");
                locale = Locale.KOREAN;
                break;
            default:
                locale = Locale.ENGLISH;
                break;
        }
        configuration.locale = locale;
//        Locale.setDefault(locale);
        resources.updateConfiguration(configuration, dm);
        Log.i(TAG, "updateConfiguration: " +  configuration.locale.getDisplayName() + ",2:" + Locale.getDefault().getDisplayLanguage());
//        ( (Activity)context).recreate();
    }
}
