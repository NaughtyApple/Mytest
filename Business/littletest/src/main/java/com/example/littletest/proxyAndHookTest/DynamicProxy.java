package com.example.littletest.proxyAndHookTest;

import android.util.Log;
import android.widget.Toast;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DynamicProxy implements InvocationHandler {

    private Object realObj;

    public DynamicProxy(Object o){
        realObj = o;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Log.i("ldld","动态代理 before:"+ method.getName() );
        if(method.getName().equals("checkPermission")){
            Log.i("ldld","强行停止startactivity试试");
            return  1;
        }else if(method.getName().equals("eat")){
            Log.i("ldld","如果是eat 方法，就给他 拦截下来...");
            return null;
        }

        Object obj;
        try {
            obj = method.invoke(realObj, args);
        } catch (Exception e) {
            obj = null;
        }
        Log.i("ldld","动态代理 after:"+ method.getName() );
        return obj;
//        return null;
    }
}
