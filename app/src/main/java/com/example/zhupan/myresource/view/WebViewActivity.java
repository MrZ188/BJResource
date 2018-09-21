package com.example.zhupan.myresource.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.zhupan.myresource.R;

public class WebViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        WebView webView = findViewById(R.id.wv_yd);
        String strURI = "happy";
        webView.loadUrl("http://dict.youdao.com/m/search?keyfrom=dict.mindex&q="
                + strURI);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return super.shouldOverrideUrlLoading(view, url);
            }
        });
//        String html = " ";
//        html += "<head>";
//        html += "<body><a href=http://www.google.com>google home</a></body>";
//        html += "</head>";
//        webView.loadData(html, "text/html", "utf-8");


    }
}
