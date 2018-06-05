package com.example.zhupan.myresource.base;

public interface BaseResultCallBackListener {
    void onSuccess(String response,int requestCode);
    void onEorror(String msg,String requestCode);
}
