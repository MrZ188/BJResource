package com.example.zhupan.myresource.utils;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class SqliteDataUtil {
    private MySqliteHelper oh;
    private SQLiteDatabase db;
    private static SqliteDataUtil instance;

    public static SqliteDataUtil getInstance() {
        if (instance == null) {
            synchronized (SqliteDataUtil.class) {
                if (instance == null) {
                    instance = new SqliteDataUtil();
                }
            }
        }
        return instance;
    }

    public void createDataBase(Context context){
        //创建帮助器对象,创建数据库
        oh = new MySqliteHelper(context, "people.db", null, 1);

        //获取数据库对象
//        db = oh.getWritableDatabase();
        db = oh.getReadableDatabase();
    }
    //创建表
    public void createTable() {

        db.execSQL("create table student(_id integer primary key autoincrement, name char(10), age integer, n integer, cpp float, math float, english float)");

    }

    //向数据库中添加数据
    public void insert() {

        //向学生表中添加10名学生
        db.execSQL("insert into student(name, age, n, cpp, math, english) values(?, ?, ?, ?, ?, ?)", new Object[]{"刘得意", 19, 1001, 60, 98, 75});
        db.execSQL("insert into student(name, age, n, cpp, math, english) values(?, ?, ?, ?, ?, ?)", new Object[]{"王锐", 20, 1002, 63, 90, 96});
        db.execSQL("insert into student(name, age, n, cpp, math, english) values(?, ?, ?, ?, ?, ?)", new Object[]{"何煜中", 19, 1003, 90, 73, 82});
        db.execSQL("insert into student(name, age, n, cpp, math, english) values(?, ?, ?, ?, ?, ?)", new Object[]{"王磊", 21, 1004, 87, 86, 92});
        db.execSQL("insert into student(name, age, n, cpp, math, english) values(?, ?, ?, ?, ?, ?)", new Object[]{"冯松", 19, 1005, 89, 98, 83});
        db.execSQL("insert into student(name, age, n, cpp, math, english) values(?, ?, ?, ?, ?, ?)", new Object[]{"裴培", 20, 1006, 75, 82, 91});
        db.execSQL("insert into student(name, age, n, cpp, math, english) values(?, ?, ?, ?, ?, ?)", new Object[]{"马骁", 19, 1007, 62, 67, 90});
        db.execSQL("insert into student(name, age, n, cpp, math, english) values(?, ?, ?, ?, ?, ?)", new Object[]{"马婧", 20, 1008, 98, 84, 87});
        db.execSQL("insert into student(name, age, n, cpp, math, english) values(?, ?, ?, ?, ?, ?)", new Object[]{"周俊升", 19, 1009, 57, 68, 96});
        db.execSQL("insert into student(name, age, n, cpp, math, english) values(?, ?, ?, ?, ?, ?)", new Object[]{"贺祺", 21, 1010, 61, 96, 72});
    }

    //删除数据库中的数据
    public void delete() {

        //删除姓名为"刘得意"的学生的信息
//        db.execSQL("delete from Student where name = ?", new Object[]{"刘得意"});
        db.execSQL("drop table student");  //删除表
    }

    //修改数据库中的数据
    public void update() {

        //将数据库中所有人的学号减少1
        db.execSQL("update student set n = n -1");

    }

    //查询数据库中的数据
    public void select() {

        //查询数据库中学生的姓名和以其对应的C++成绩,返回值为一个结果集
        Cursor cursor = db.rawQuery("select name,n, cpp from student", null);

        while (cursor.moveToNext()) {

            //cursor.getColumnIndex("name")获得姓名所在的列
            String name = cursor.getString(cursor.getColumnIndex("name"));
            float cpp = cursor.getFloat(cursor.getColumnIndex("cpp"));
            int n = cursor.getInt(cursor.getColumnIndex("n"));
            //输出学生的姓名和与姓名对应的C++成绩
            Log.d("MainActivity", '[' + name + ", " + n + "," + cpp + ']');
        }
    }
}
