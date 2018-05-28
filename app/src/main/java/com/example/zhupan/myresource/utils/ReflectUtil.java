package com.example.zhupan.myresource.utils;

public class ReflectUtil {
    /**
     * 根据完整名获取class对象
     *
     * @param className
     * @return
     */
    public static Class getClass(String className) throws ClassNotFoundException {
        Class a = null;
        a = Class.forName(className);
        return a;
    }

    /**
     * 得到class后，创建对象，并修改成员变量，调用方法
     *
     * @param className
     */
    public static void modifyFieldToClass(String className) throws ClassNotFoundException {
        Class a = Class.forName(className);

    }
}
