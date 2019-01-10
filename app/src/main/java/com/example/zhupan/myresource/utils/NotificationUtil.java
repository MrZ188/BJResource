package com.example.zhupan.myresource.utils;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;

import com.example.zhupan.myresource.MainActivity;
import com.example.zhupan.myresource.R;

public class NotificationUtil {
    public static void sendNotification(Activity activity) {

        //第一步：实例化通知栏构造器Notification.Builder：
        Notification.Builder builder = new Notification.Builder(activity);//实例化通知栏构造器Notification.Builder，参数必填（Context类型），为创建实例的上下文
        //第二步：获取状态通知栏管理：
        NotificationManager mNotifyMgr = (NotificationManager) activity.getSystemService(Context.NOTIFICATION_SERVICE);//获取状态栏通知的管理类（负责发通知、清除通知等操作）
        //第三步：设置通知栏PendingIntent（点击动作事件等都包含在这里）:
        Intent push = new Intent(activity, MainActivity.class);//新建一个显式意图，第一个参数 Context 的解释是用于获得 package name，以便找到第二个参数 Class 的位置
        //PendingIntent可以看做是对Intent的包装，通过名称可以看出PendingIntent用于处理即将发生的意图，而Intent用来用来处理马上发生的意图
//本程序用来响应点击通知的打开应用，第二个参数非常重要，点击notification 进入到activity, 使用到pendingIntent类方法，PengdingIntent.getActivity()的第二个参数，即请求参数，实际上是通过该参数来区别不同的Intent的，如果id相同，就会覆盖掉之前的Intent了
        PendingIntent contentIntent = PendingIntent.getActivity(activity, 0, push, PendingIntent.FLAG_CANCEL_CURRENT);
//第四步：对Builder进行配置：
        builder
                .setContentTitle("My notification")//标题
                .setContentText("Hello World!")// 详细内容
                .setContentIntent(contentIntent)//设置点击意图
                .setTicker("New message")//第一次推送，角标旁边显示的内容
                .setLargeIcon(BitmapFactory.decodeResource(activity.getResources(), R.mipmap.ic_launcher))
                .setDefaults(Notification.DEFAULT_ALL);//打开呼吸灯，声音，震动，触发系统默认行为
/*Notification.DEFAULT_VIBRATE    //添加默认震动提醒  需要VIBRATE permission
Notification.DEFAULT_SOUND    //添加默认声音提醒
Notification.DEFAULT_LIGHTS//添加默认三色灯提醒
Notification.DEFAULT_ALL//添加默认以上3种全部提醒*/
//.setLights(Color.YELLOW, 300, 0)//单独设置呼吸灯，一般三种颜色：红，绿，蓝，经测试，小米支持黄色
//.setSound(url)//单独设置声音
//.setVibrate(new long[] { 100, 250, 100, 250, 100, 250 })//单独设置震动
//比较手机sdk版本与Android 5.0 Lollipop的sdk
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            builder
/*android5.0加入了一种新的模式Notification的显示等级，共有三种：
VISIBILITY_PUBLIC只有在没有锁屏时会显示通知
VISIBILITY_PRIVATE任何情况都会显示通知
VISIBILITY_SECRET在安全锁和没有锁屏的情况下显示通知*/
                    .setVisibility(Notification.VISIBILITY_PUBLIC)
                    .setPriority(Notification.PRIORITY_DEFAULT)//设置该通知优先级
                    .setCategory(Notification.CATEGORY_MESSAGE)//设置通知类别
//.setColor(context.getResources().getColor(R.color.small_icon_bg_color))//设置smallIcon的背景色
                    .setFullScreenIntent(contentIntent, true)//将Notification变为悬挂式Notification
                    .setSmallIcon(R.mipmap.ic_launcher);//设置小图标
        } else {
            builder
                    .setSmallIcon(R.mipmap.ic_launcher);//设置小图标
        }
        //第五步：发送通知请求：
        Notification notify = null;//得到一个Notification对象
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
            notify = builder.build();
        }
        mNotifyMgr.notify(123456, notify);//发送通知请求
    }

}
