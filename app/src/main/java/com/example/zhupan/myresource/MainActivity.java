package com.example.zhupan.myresource;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.zhupan.myresource.utils.AdvertisingIdClient;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Thread(new Runnable() {
            public void run() {
                try {
                    AdvertisingIdClient.AdInfo adInfo = AdvertisingIdClient
                            .getAdvertisingIdInfo(MainActivity.this);
                    String advertisingId = adInfo.getId();
                    Boolean optOutEnabled = adInfo.isLimitAdTrackingEnabled();
                    Log.i(TAG, "advertisingId" + advertisingId);
                    // Log.i("ABC", "optOutEnabled" + optOutEnabled);
                } catch (Exception e) {
                    Log.i(TAG, "run:"+e.getLocalizedMessage());
                    e.printStackTrace();
                }
//                mHandler.sendEmptyMessage(HANDEL_ADID);
            }
        }).start();
    }
}
