package com.example.zhupan.myresource.utils;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Display;
import android.view.WindowManager;

import com.example.zhupan.myresource.R;

public class MyDialog extends Dialog{
    Context context;
    public MyDialog( Context context) {
        super(context, R.style.bjmgf_sdk_Dialog);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_initview);
        setView();
    }

    private void setView() {
        /*
         * 将对话框的大小按屏幕大小的百分比设置
         */
        WindowManager m = getWindow().getWindowManager();
        Display d = m.getDefaultDisplay(); // 获取屏幕宽、高用
        WindowManager.LayoutParams p = getWindow().getAttributes(); // 获取对话框当前的参数值
        p.height = (int) (d.getHeight() * 0.6); // 高度设置为屏幕的0.6
        p.width = (int) (d.getWidth() * 0.65); // 宽度设置为屏幕的0.65
        getWindow().setAttributes(p);
    }
}
