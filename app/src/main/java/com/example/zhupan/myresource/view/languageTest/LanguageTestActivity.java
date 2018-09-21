package com.example.zhupan.myresource.view.languageTest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.zhupan.myresource.R;

public class LanguageTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language_test);
        TextView title = findViewById(R.id.text_title);
        title.setBackgroundResource(R.drawable.bjmgf_sdk_float_left_bg);
        Button btn = findViewById(R.id.btn);
        btn.setText(R.string.dialog_title2);
    }
}
