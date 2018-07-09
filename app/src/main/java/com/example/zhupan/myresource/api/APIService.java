package com.example.zhupan.myresource.api;

import com.example.zhupan.myresource.entity.ClassfyBean;

import retrofit2.http.GET;
import rx.Observable;

public interface APIService {
    @GET("appCheck.do")
    Observable<ClassfyBean> getClassfyBean();

}
