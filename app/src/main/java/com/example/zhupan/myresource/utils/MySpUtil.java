package com.example.zhupan.myresource.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class MySpUtil {
    public static String getStringValue(Context context, String key, String defaultValue) {
        SharedPreferences sp = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
        String result = "";
        if (sp != null) {
            result = sp.getString(key, defaultValue);
        }
        return result;
    }

    public static void setStringValue(Context context, String key, String value) {
        SharedPreferences sp = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
        Editor edit = sp.edit();
        edit.putString(key, value);
        edit.commit();
    }
}
