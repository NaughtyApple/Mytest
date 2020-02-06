package com.example.pluginactivity.myproxy;

import android.util.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class myProxyHandler implements InvocationHandler {

    /**被代理的对象*/
    private Object o;
    public myProxyHandler(Object o) {
        super();
        this.o = o;
    }
    /**
     * 自己的开始业务
     * @param str
     */
    private void beforeMethod(String str){
        Log.i("ldld","方法"+str+"()开始~~");
    }

    /**
     * 自己的结束业务
     * @param str
     */
    private void afterMethod(String str){
        Log.i("ldld","方法"+str+"()结束~~");
    }
    @Override
    public Object invoke(Object arg0, Method arg1, Object[] arg2)
            throws Throwable {
        //自己的业务
        beforeMethod(arg1.getName());
        //被代理对象的业务,是通过反射来实现的
        arg1.invoke(o, arg2);
        //自己的业务
        afterMethod(arg1.getName());
        return null;
    }
}

