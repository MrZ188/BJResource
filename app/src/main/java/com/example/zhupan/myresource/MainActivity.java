package com.example.zhupan.myresource;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.zhupan.myresource.api.APIService;
import com.example.zhupan.myresource.base.BaseActivity;
import com.example.zhupan.myresource.config.Constants;
import com.example.zhupan.myresource.entity.ClassfyBean;
import com.example.zhupan.myresource.entity.Student;
import com.example.zhupan.myresource.utils.InitViewDialog;
import com.example.zhupan.myresource.utils.ListTest;
import com.example.zhupan.myresource.utils.MySpUtil;
import com.example.zhupan.myresource.utils.MyUtil;
import com.example.zhupan.myresource.utils.ReflectUtil;
import com.example.zhupan.myresource.utils.SdUtil;
import com.example.zhupan.myresource.utils.SqliteDataUtil;
import com.example.zhupan.myresource.utils.TipsDialog;
import com.example.zhupan.myresource.utils.ToastUtil;
import com.example.zhupan.myresource.view.WebViewActivity;
import com.example.zhupan.myresource.view.languageTest.LanguageTestActivity;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.tweetcomposer.TweetComposer;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

public class MainActivity extends BaseActivity {
    private static final String TAG = "MainActivity";

    @BindView(R.id.btn_cn)
    Button btnCn;
    @BindView(R.id.btn_en)
    Button btnEn;
    @BindView(R.id.btn_dialog)
    Button btnDialog;
    private MySdk mySdk;
    private int count = 2;

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

    private void chooseKorean() {
        mySdk.setLanguage(this, Constants.KR);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @OnClick({R.id.btn_cn, R.id.btn_en, R.id.btn_kr, R.id.btn_dialog, R.id.btn_sp, R.id.btn_sp2, R.id.btn_reflect, R.id.btn_save_text, R.id.btn_comments,
            R.id.btn_list_test, R.id.btn_init_dialog, R.id.btn_sqlite_test, R.id.btn_twitter_share, R.id.btn_rx_java, R.id.btn_rx_android, R.id.btn_facebook, R.id.btn_mac})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_cn:
//                chooseChinese();
                //获取
//                new Thread(new Runnable() {
//                    public void run() {
//                        try {
//                            Log.i(TAG, "run: lalala");
//                            AdvertisingIdClient.AdInfo adInfo = AdvertisingIdClient
//                                    .getAdvertisingIdInfo(MainActivity.this);
//                            String advertisingId = adInfo.getId();
//                            Log.i(TAG, "advertisingId:" + advertisingId);
//                        } catch (Exception e) {
//                            Log.i(TAG, "run:" + e.getLocalizedMessage());
//                            e.printStackTrace();
//                        }
//                    }
//                }).start();

//                goTo(MainActivity.this,WebViewActivity.class);

//                List<Student> list = null;
//                try {
//                    list = MyUtil.pull2xml(getResources().getAssets().open("students.xml"));
//                    Log.i(TAG, "onViewClicked: " + list.toString());
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
                Timer timer = new Timer();
                timer.scheduleAtFixedRate(new MyTask(), 1, 2000);      //隔几秒，干某事
                break;
            case R.id.btn_en:
                chooseEnglish();
                break;
            case R.id.btn_kr:
                chooseKorean();
                break;

            case R.id.btn_dialog:
                TipsDialog dialog = new TipsDialog(MainActivity.this, getString(R.string.dialog_title), getString(R.string.dialog_context), getString(R.string.dialog_context)
                        , getString(R.string.dialog_context), new TipsDialog.DialogCallBack() {
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
                SdUtil.saveToLocal(MainActivity.this, "当今十大美女，据武林妙云斋专业统计，分别是：王翠花，赵小丫，" +
                        "刘大婶，郭大嫂，李大妈，牛桂花，黄小姐，胡晓鸡，七小八，美小十。众多美女，有的国色天香，有的小家碧玉，有的。。。真是" +
                        "争奇斗艳，风光一时无俩。");
                break;
            case R.id.btn_comments:
//                goTo(this, CommentsActivity.class);
                goTo(this, LanguageTestActivity.class);
                break;
            case R.id.btn_list_test:
                ListTest.listTest();
                break;
            case R.id.btn_init_dialog:
                InitViewDialog initViewDialog = new InitViewDialog(this);
                initViewDialog.show();
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
            case R.id.btn_sqlite_test:
                SqliteDataUtil.getInstance().createDataBase(this);
//                SqliteDataUtil.getInstance().createTable();
//                SqliteDataUtil.getInstance().insert();
                SqliteDataUtil.getInstance().delete();
//                SqliteDataUtil.getInstance().update();
                SqliteDataUtil.getInstance().select();
                break;
            /**
             * rx的好处大概就是异步操作无论怎么延时最终结果都可以被处理
             */
            case R.id.btn_rx_java:
                Subscriber subscriber = new Subscriber<String>() {
                    @Override
                    public void onStart() {                     //初始化时的事件处理
                        super.onStart();
                        Log.i(TAG, "onStart: ");
                    }

                    @Override
                    public void onCompleted() {
                        Log.i(TAG, "onCompleted: ");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG, "onError: ");
                    }

                    @Override
                    public void onNext(String o) {
                        Log.i(TAG, "onNext: " + o);
                    }
                };

                Observable.create(new Observable.OnSubscribe<String>() {      //一步到位，创建被观察者、观察者、订阅事件
                    @Override
                    public void call(Subscriber<? super String> subscriber) {
                        subscriber.onStart();
                        subscriber.onNext("1");
                        subscriber.onNext("2");
                        subscriber.onNext("3");
                        subscriber.onCompleted();

                    }
                }).subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String s) {

                    }
                });
                Observable observable1 = Observable.just("123");
//                observable.subscribe(subscriber);
//                observable.unsafeSubscribe(subscriber);    //取消订阅
                break;
            case R.id.btn_rx_android:
                Log.i(TAG, "onViewClicked: ");
                Retrofit retrofit = new Retrofit.Builder()                       //retrofit请求对象,传接口首
                        .baseUrl("https://sdk.friendtimes.kr/gf/app/sdk/")
                        .addConverterFactory(GsonConverterFactory.create())      //gson解析
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .build();

                APIService apiService = retrofit.create(APIService.class);      //接口尾，绑结果bean

                apiService.getClassfyBean()                                     //输出结果
                        //设置事件触发在非主线程
                        .subscribeOn(Schedulers.io())
                        //设置事件接受在UI线程以达到UI显示的目的
//                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<ClassfyBean>() {
                            @Override
                            public void onCompleted() {
                            }

                            @Override
                            public void onError(Throwable e) {
                            }

                            @Override
                            public void onNext(ClassfyBean classfyBean) {
                                Log.i(TAG, "onNext: " + classfyBean.msg);
//                                Toast.makeText(MainActivity.this, classfyBean.msg, Toast.LENGTH_SHORT).show();
                            }
                        });
                break;
            case R.id.btn_facebook:
                launchFacebook();
                break;
            case R.id.btn_mac:
                ToastUtil.showMessage(MainActivity.this, MyUtil.getInstance().getMac());
                break;
        }
    }

    private class MyTask extends TimerTask {

        @Override
        public void run() {
            Log.i(TAG, "run: " + (count++) + " girls are shopping~~");
        }
    }

    public final void launchFacebook() {

        String urlBrowser = "https://www.facebook.com/RoyalChaosWG/131502057527128";
//        final String urlFb = "fb://facewebmodal/f?href=" + urlBrowser;
//        Intent intent = new Intent(Intent.ACTION_VIEW);
//        intent.setData(Uri.parse(urlFb));
        final PackageManager packageManager = this.getPackageManager();
        Intent intent = newFacebookIntent(packageManager, urlBrowser);
        List<ResolveInfo> list = packageManager.queryIntentActivities(intent,
                PackageManager.MATCH_DEFAULT_ONLY);//过滤查找是否安装了facebook应用
        if (list.size() == 0) {    //没安装
            intent.setData(Uri.parse(urlBrowser));
        }
        startActivity(intent);
    }


    public static Intent newFacebookIntent(PackageManager pm, String url) {
        Uri uri = Uri.parse(url);
        try {
            ApplicationInfo applicationInfo = pm.getApplicationInfo("com.facebook.katana", 0);
            if (applicationInfo.enabled) {
// http://stackoverflow.com/a/24547437/1048340
                Log.i(TAG, "newFacebookIntent: " + "fb://facewebmodal/f?href=" + url);
//                uri = Uri.parse("fb://facewebmodal/131502057527128");
                uri = Uri.parse("fb://page/131502057527128");
            }
        } catch (PackageManager.NameNotFoundException ignored) {
        }
        return new Intent(Intent.ACTION_VIEW, uri);

    }

    private void launchFaceBookApp() {
        Intent intent1 = new Intent(Intent.ACTION_VIEW);
        intent1.setData(Uri.parse(getFacebookPageURL(MainActivity.this)));
        startActivity(intent1);

    }

    //这个方法是为了生成标准的可用于跳转的Facebook url
    public String getFacebookPageURL(Context context) {
        //这一个要是你的Facebook用户名
        String mFacebookPageId = "131502057527128";
        String mFacebookUrl = "https://www.facebook.com/RoyalChaosWG/" + mFacebookPageId;
        PackageManager packageManager = context.getPackageManager();
//        try {
//            Log.i(TAG, "getFacebookPageURL: new");
        Log.i(TAG, "getFacebookPageURL: " + "fb://facewebmodal/f?href=" + mFacebookUrl);
        return "fb://facewebmodal/f?href=" + mFacebookUrl;
//        } catch (PackageManager.NameNotFoundException e) {
//            return mFacebookUrl; //要是没有安装就用普通的url
//        }
    }
}
