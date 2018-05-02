package com.example.zhupan.myresource.base;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

import com.example.zhupan.myresource.R;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_base);
    }

}
