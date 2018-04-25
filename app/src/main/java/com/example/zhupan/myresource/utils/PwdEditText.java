package com.example.zhupan.myresource.utils;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * author:Zhupan
 * time:2018/4/24  16:07
 */
public class PwdEditText extends RelativeLayout implements TextWatcher, View.OnClickListener{
    private EditText editText;
    private ImageView imageView;
    private TextView textView;
    private boolean isClick = false;
    private Context mContext;

    public PwdEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        editText = new EditText(context,attrs);
        this.mContext = context;

    }

    public PwdEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public void onClick(View v) {

    }

    private void initWithAttrs(Context context,AttributeSet attrs){

        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        addView(editText,params);
    }
}
