package com.example.zhupan.myresource.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.example.zhupan.myresource.R;

public class BaseActivity extends AppCompatActivity {
    public final String TAG = this.getClass().getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_base);
    }
    public void goTo(Context activity, Class<?> cls){
        Intent intent = new Intent(activity,cls);
        activity.startActivity(intent);
    }

    public void goTo(Context activity,Class<?> cls,Bundle bundle){
        Intent intent = new Intent(activity,cls);
        intent.putExtras(bundle);
        activity.startActivity(intent);
     }
}
