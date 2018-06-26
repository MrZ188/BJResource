package com.example.zhupan.myresource.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.WindowManager;
import com.example.zhupan.myresource.R;
public class InitViewDialog extends Dialog {
    private final String TAG = InitViewDialog.class.getSimpleName();

    //    protected PageManager manager;
//    protected ViewGroup root;
//    @SuppressWarnings("unused")
//    private Activity activity;
    private Context context;

    private float Landscape_Width_Percent_Normal = 0.6f;
    private float Portrait_Width_Percent_Normal = 0.9f;
    private final float Width_Percent_Full = 1.0f;
    private final int Size_Min = 300;
    private final int Size_Max = 850;

    private int pageType;
    private boolean isTablet;

    public InitViewDialog(Context context) {
        super(context, R.style.bjmgf_sdk_Dialog);
    }

//    public InitViewDialog(Context context, int theme) {
//        super(context, theme);
//    }

    private void initDialog(Activity activity, Context context) {
//        this.activity = activity;
        this.context = context;
//        pageType = type;
//        manager = new PageManager();
        setCanceledOnTouchOutside(false);
//        isTablet = false;
//        Portrait_Width_Percent_Normal = isTablet ? 0.5f : 0.9f;
//        Landscape_Width_Percent_Normal = Utility.isTablet(context) ? 0.4f : 0.6f;
//        Landscape_Width_Percent_Normal = isTablet ? 0.4f : 0.6f;
        Log.d(TAG, "Portrait_Width_Percent_Normal=" + Portrait_Width_Percent_Normal);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_initview);
//        root = (ViewGroup) findViewById(ReflectResourceId.getViewId(getContext(),
//                Resource.id.bjmgf_sdk_dialog_root));
//        manager.setRoot(root);
//
//
//        createPage();
        fitScreenMode();


    }

    @Override
    public void show() {
//        fitScreenMode();
        super.show();
    }

    @Override
    public void dismiss() {

//        manager.clean();
        Log.i(TAG, "dismiss");
        super.dismiss();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        /**
         * 点击输入框外使输入框隐藏
         */
        hideInputWindow();
//		if(hideInputWindow()) {
//			return true;
//		}
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            if (manager != null && manager.getCurrentPage() != null) {
//                if (!manager.getCurrentPage().canBack()) {
//                    return true;
//                }
//            }
        }
        return super.onKeyDown(keyCode, event);
    }

    public void fitScreen(boolean full) {
        WindowManager windowManager = null;
        if(getWindow()!=null){
             windowManager = getWindow().getWindowManager();
//            DisplayMetrics dm = new DisplayMetrics();
            Display d = windowManager.getDefaultDisplay();
//            d.getMetrics(dm);    //d.height方法过时
            WindowManager.LayoutParams params = getWindow().getAttributes();
            boolean landscape = false;     //竖
            Log.i(TAG, "fitScreen: "+d.getWidth()+","+d.getWidth()*0.9);
            params.width = (int) (d.getWidth()*0.9);
//            params.width = getFitWidth(dm.widthPixels, landscape, full);
            params.height = (int) (d.getHeight()*0.5);
//            params.height = getFitHeight(dm.heightPixels, landscape, full);
//            Log.i(TAG, "screen width=" + dm.widthPixels + ", height=" + dm.heightPixels
//                    + "width=" + params.width + ", height=" + params.height);
//        manager.setSize(params.width, params.height);
            getWindow().setAttributes(params);
//            getWindow().setGravity(Gravity.CENTER);
        }
//        DisplayMetrics dm = new DisplayMetrics();
//        windowManager.getDefaultDisplay().getMetrics(dm);
//        WindowManager.LayoutParams params = getWindow().getAttributes();
//        boolean landscape = AppInfoData.getScreenOrientation() ==
//                SysConstants.BJMGF_Screen_Orientation_Landscape;



    }

    private int getFitWidth(int screenWidth, boolean landscape, boolean full) {
        int width = 0;
        if (full) {
            width = (int) (screenWidth * Width_Percent_Full);
        } else {
            width = (int) (landscape ? screenWidth * Landscape_Width_Percent_Normal
                    : screenWidth * Portrait_Width_Percent_Normal);
        }
        width = checkMax(width);
        width = checkMin(width);
        return width;
    }

    private int getFitHeight(int screenHeight, boolean landscape, boolean full) {
        /** 窗体高度自适应 */
        return ViewGroup.LayoutParams.WRAP_CONTENT;
//		int height = 0;
//		if (full) {
//			height = (int)(screenHeight * Height_Percent_Full);
//		} else {
//			height = (int)(landscape ? screenHeight * Landscape_Height_Percent_Normal
//					: screenHeight * Portrait_Height_Percent_Normal);
//		}
//		return checkMin(height);
    }

    private int checkMin(int size) {
        WindowManager windowManager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(dm);
        Log.d(TAG, "dialogPage dm.density=" + dm.density);
        Log.d(TAG, "dialogPage min=" + Size_Min);
        return size < Size_Min ? Size_Min : size;
    }

    private int checkMax(int size) {
        WindowManager windowManager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(dm);
        Log.d(TAG, "dialogPage with size=" + size);
//        if (!Utility.isTablet(context)) {
        if (isTablet)
            return size;
//        }
        return size > Size_Max ? Size_Max : size;
    }

    protected void fitScreenMode() {
//		Log.i(TAG, "show()");
        fitScreen(false);
    }


    public boolean hideInputWindow() {
        return Utility.hideInputMethod(getContext(), getCurrentFocus());
    }

    public int getPageType() {
        return pageType;
    }

//    public Activity getActivity() {
//        return activity;
//    }
}
