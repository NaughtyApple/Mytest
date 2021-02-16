package com.example.littletest.proxyAndHookTest;

import android.util.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class InstrumentationProxy implements InvocationHandler {

    private Object realObj;

    public InstrumentationProxy(Object o){
        realObj = o;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Log.i("ldld","InstrumentationProxy 动态代理 before:"+ method.getName() );

        Log.i("ldld","InstrumentationProxy 动态代理 after:"+ method.getName() );
        return null;
    }
}


