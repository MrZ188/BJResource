package com.example.zhupan.myresource.utils;

import android.util.Log;

import java.util.ArrayList;

public class ListTest {
    private static final String TAG = "haha";

    public static void listTest() {
        ArrayList<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");

        for (int i = 0; i < list.size(); i++) {
            Log.i(TAG, "listTest: " + list.size() + "," + list.get(i));
            if (list.get(i).equals("2")) {
                list.remove(i);
            }
        }
    }
}
