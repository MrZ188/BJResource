package com.example.zhupan.myresource;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.zhupan.myresource.base.BaseActivity;
import com.example.zhupan.myresource.config.Constants;
import com.example.zhupan.myresource.utils.InitViewDialog;
import com.example.zhupan.myresource.utils.ListTest;
import com.example.zhupan.myresource.utils.MyDialog;
import com.example.zhupan.myresource.utils.MySpUtil;
import com.example.zhupan.myresource.utils.ReflectUtil;
import com.example.zhupan.myresource.utils.SdUtil;
import com.example.zhupan.myresource.utils.TipsDialog;
import com.example.zhupan.myresource.view.comments.impl.CommentsActivity;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.tweetcomposer.TweetComposer;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {
    private static final String TAG = "MainActivity";

    @BindView(R.id.btn_cn)
    Button btnCn;
    @BindView(R.id.btn_en)
    Button btnEn;
    @BindView(R.id.btn_dialog)
    Button btnDialog;
    private MySdk mySdk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mySdk = MySdk.getInstant();
        Twitter.initialize(this);   //twitter初始化
    }

    private void chooseChinese() {
        mySdk.setLanguage(this, Constants.CN);
    }

    private void chooseEnglish() {
        mySdk.setLanguage(this, Constants.EN);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @OnClick({R.id.btn_cn, R.id.btn_en, R.id.btn_dialog, R.id.btn_sp, R.id.btn_sp2, R.id.btn_reflect,R.id.btn_save_text,R.id.btn_comments,
            R.id.btn_list_test,R.id.btn_init_dialog,R.id.btn_twitter_login,R.id.btn_twitter_share})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_cn:
                chooseChinese();
                break;
            case R.id.btn_en:
                chooseEnglish();
                break;
            case R.id.btn_dialog:
                TipsDialog dialog = new TipsDialog(MainActivity.this, getString(R.string.dialog_title), getString(R.string.dialog_context), "好"
                        , "不好", new TipsDialog.DialogCallBack() {
                    @Override
                    public void negativeClick() {
                        Log.i(TAG, "negativeClick: ");
                    }

                    @Override
                    public void positiveClick() {
                        Log.i(TAG, "positiveClick: ");
                    }
                });
                dialog.show();
                dialog.setCanceledOnTouchOutside(true);
                break;
            case R.id.btn_sp:
                MySpUtil.setStringValue(this, "sptest", "烽火连城");
                break;
            case R.id.btn_sp2:
                String s = MySpUtil.getStringValue(this, "sptest", "");
                Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_reflect:
                Class reflect = null;
                try {
                    reflect = ReflectUtil.getClass("com.example.zhupan.myresource.entity.Reflect");
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                Method[] methods = reflect.getDeclaredMethods();
                Field[] fields = reflect.getDeclaredFields();
                for (Method method : methods) {
                    Log.i(TAG, "onViewClicked: " + method.getName());
                }
                Log.i(TAG, "onViewClicked: ----------------------------------------------------");
                for (Field field : fields) {
                    Log.i(TAG, "onViewClicked: " + field.getName());
                }
                break;
            case R.id.btn_save_text:
                SdUtil.saveToLocal(MainActivity.this,"当今十大美女，据武林妙云斋专业统计，分别是：王翠花，赵小丫，" +
                        "刘大婶，郭大嫂，李大妈，牛桂花，黄小姐，胡晓鸡，七小八，美小十。众多美女，有的国色天香，有的小家碧玉，有的。。。真是" +
                        "争奇斗艳，风光一时无俩。");
                break;
            case R.id.btn_comments:
                goTo(this, CommentsActivity.class);
                break;
            case R.id.btn_list_test:
                ListTest.listTest();
                break;
            case R.id.btn_init_dialog:
                InitViewDialog initViewDialog = new InitViewDialog(this);
                initViewDialog.show();
                break;
            case R.id.btn_twitter_login:
                Log.i(TAG, "onViewClicked: "+String.valueOf(null));
                break;
            case R.id.btn_twitter_share:
                try {
                    TweetComposer.Builder builder = new TweetComposer.Builder(MainActivity.this)
                            .url(new URL("https://www.google.com/"));
                    builder.show();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }

                break;
        }
    }
}
