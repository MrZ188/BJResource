package com.example.zhupan.myresource.utils;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.zhupan.myresource.R;

/**
 * author:Zhupan
 * time:2018/4/24  9:59
 */
public class TipsDialog extends Dialog {
    private static final String TAG = TipsDialog.class.getCanonicalName();

    private RelativeLayout mDialogLayout;
    private TextView mTitleTextView;
    private TextView mMessageTextView;
    private Button mPositiveButton, mNegativeButton;

    private Window mDialogWindow = null;
    private WindowManager.LayoutParams mLayoutParams = null;

    public boolean mIsPositiveButton = false;
    public boolean mIsNegativeButton = false;

    private float Landscape_Width_Percent_Normal = 0.5f;
    private float Landscape_Height_Percent_Normal = 0.4f;
    private float Portrait_Width_Percent_Normal = 0.8f;
    private float Portrait_Height_Percent_Normal = 0.3f;

    private final int Size_Max = 850;
    private View divideView;
    private Context mContext;
    private TextView txtTitle;
    public TipsDialog(Context context) {
        super(context);
        this.mContext = context;
        this.setCancelable(false);
        InitUI(mContext);
    }
    private void InitUI(Context context){
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.dialog_tips);
        txtTitle = findViewById(R.id.txt_title);

    }
    @Override
    public void show(){
        fitScreen();
        super.show();
    }

    public void setDialogSize(int width, int height) {
        mLayoutParams.width = width;
        mLayoutParams.height = height;
        mDialogWindow.setAttributes(mLayoutParams);
    }
    private void fitScreen() {
        WindowManager windowManager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(dm);
        mDialogWindow = this.getWindow();
        mLayoutParams = mDialogWindow.getAttributes();
//        boolean landscapeFlag = dm.widthPixels > dm.heightPixels;
        boolean landscapeFlag = isScreenOriatationLandscape(mContext);
        Log.d(TAG, "landscapeFlag " + landscapeFlag);
        mLayoutParams.width = (int) (dm.widthPixels * (landscapeFlag ? Landscape_Width_Percent_Normal : Portrait_Width_Percent_Normal));
        mLayoutParams.height = (int) (dm.heightPixels * (landscapeFlag ? Landscape_Height_Percent_Normal : Portrait_Height_Percent_Normal));
        mLayoutParams.width = checkMax(mLayoutParams.width);
        mLayoutParams.height = checkMax(mLayoutParams.height);
        mLayoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        mDialogWindow.setAttributes(mLayoutParams);
        mDialogWindow.setGravity(Gravity.CENTER);
        mDialogWindow.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.drawable_dialog_background));
    }

    /**
     * false portrait
     * true landscape
     *
     * @param context
     * @return
     */
    private boolean isScreenOriatationLandscape(Context context) {
        return context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
    }

    private int checkMax(int size) {
        WindowManager windowManager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(dm);
        if (!isTablet(getContext())) {
            return size;
        }
        return size > Size_Max ? Size_Max : size;
    }

    public boolean isTablet(Context context) {
        return (context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }
}
