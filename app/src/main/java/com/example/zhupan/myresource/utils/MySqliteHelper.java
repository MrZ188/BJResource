package com.example.zhupan.myresource.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/*      onCreate()	创建数据库	创建数据库时自动调用
        onUpgrade()	升级数据库
        close()	关闭所有打开的数据库对象
        execSQL()	可进行增删改操作, 不能进行查询操作
        query()、rawQuery()	查询数据库
        insert()	插入数据
        delete()	删除数据
        getWritableDatabase()	创建或打开可以读/写的数据库	通过返回的SQLiteDatabase对象对数据库进行操作
        getReadableDatabase()	创建或打开可读的数据库	同上*/
public class MySqliteHelper extends SQLiteOpenHelper {
    private static final String TAG = "MySqliteHelper";
    public MySqliteHelper(Context context, String name, SQLiteDatabase.CursorFactory cursorFactory, int version) {
        super(context, name, cursorFactory, version);
    }

    //第一次创建数据库的时候回调该方法
    //当使用getReadableDatabase()方法获取数据库实例的时候, 如果数据库不存在, 就会调用这个方法;

    //作用：创建数据库表：将创建数据库表的 execSQL()方法 和 初始化表数据的一些 insert()方法写在里面;
    @Override
    public void onCreate(SQLiteDatabase db) {

        //SQLite数据创建支持的数据类型： 整型数据，字符串类型，日期类型，二进制的数据类型
        //创建了一个名为person的表
        //创建一个学生表
        //execSQL用于执行SQL语句
        //完成数据库的创建
        //数据库实际上是没有被创建或者打开的，直到getWritableDatabase() 或者 getReadableDatabase() 方法中的一个被调用时才会进行创建或者打开
        //见下代码
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO 更改数据库版本的操作
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        // TODO 每次成功打开数据库后首先被执行
    }

}