package com.example.zhupan.myresource;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.zhupan.myresource.api.APIService;
import com.example.zhupan.myresource.base.BaseActivity;
import com.example.zhupan.myresource.config.Constants;
import com.example.zhupan.myresource.entity.ClassfyBean;
import com.example.zhupan.myresource.utils.InitViewDialog;
import com.example.zhupan.myresource.utils.ListTest;
import com.example.zhupan.myresource.utils.MySpUtil;
import com.example.zhupan.myresource.utils.ReflectUtil;
import com.example.zhupan.myresource.utils.SdUtil;
import com.example.zhupan.myresource.utils.SqliteDataUtil;
import com.example.zhupan.myresource.utils.SuperMan;
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
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
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

    @OnClick({R.id.btn_cn, R.id.btn_en, R.id.btn_dialog, R.id.btn_sp, R.id.btn_sp2, R.id.btn_reflect, R.id.btn_save_text, R.id.btn_comments,
            R.id.btn_list_test, R.id.btn_init_dialog, R.id.btn_sqlite_test, R.id.btn_twitter_share, R.id.btn_rx_java, R.id.btn_rx_android})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_cn:
//                chooseChinese();
                SuperMan superMan = new SuperMan();
                superMan.eat();
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
                SdUtil.saveToLocal(MainActivity.this, "当今十大美女，据武林妙云斋专业统计，分别是：王翠花，赵小丫，" +
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
                }).subscribe(new Observer<String>() {
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
                Retrofit retrofit = new Retrofit.Builder()                       //retrofit请求对象
                        .baseUrl("https://sdk.friendtimes.kr/gf/app/sdk/")
                        .addConverterFactory(GsonConverterFactory.create())      //gson解析
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .build();

                APIService apiService = retrofit.create(APIService.class);

                apiService.getClassfyBean()
                        //设置事件触发在非主线程
                        .subscribeOn(Schedulers.io())
                        //设置事件接受在UI线程以达到UI显示的目的
                        .observeOn(AndroidSchedulers.mainThread())
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
                                Toast.makeText(MainActivity.this, classfyBean.msg, Toast.LENGTH_SHORT).show();
                            }
                        });
                break;
        }
    }
}
