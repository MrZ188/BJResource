package com.example.zhupan.myresource.utils;

import android.util.Log;

public class SuperMan extends Man{
    @Override
    public void eat() {
        Log.i("hahaha", "eat: shit");
        super.eat();
    }
}
