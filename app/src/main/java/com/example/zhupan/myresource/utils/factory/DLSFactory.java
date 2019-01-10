package com.example.zhupan.myresource.utils.factory;

import android.util.Log;

import com.example.zhupan.myresource.utils.factory.DLSFactoryProducts.DLSAbstract;
import com.example.zhupan.myresource.utils.factory.DLSFactoryProducts.DLSSlippery;
import com.example.zhupan.myresource.utils.factory.DLSFactoryProducts.DLSUltrathin;

/**
 * 套套加工厂
 */
public class DLSFactory {
    private static final String TAG = "DLSFactory";
    private static DLSFactory dlsFactory;
    DLSAbstract dlsAbstract = null;

    public static DLSFactory getInstance() {
        if (null == dlsFactory) {
            synchronized (DLSFactory.class) {
                if (null == dlsFactory)
                    dlsFactory = new DLSFactory();
            }
        }
        return dlsFactory;
    }

    public void createDLSTT(int type) {
        switch (type) {
            case 1:
                dlsAbstract = new DLSSlippery();
                break;
            case 2:
                dlsAbstract = new DLSUltrathin();
                break;
            default:
                dlsAbstract = new DLSAbstract();
                break;
        }
        Log.i(TAG, "createDLSTT: 请拿好您的TT，祝您生活愉快，性福美满~~~");
    }
}
