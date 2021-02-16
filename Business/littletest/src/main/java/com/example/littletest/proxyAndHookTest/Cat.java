package com.example.littletest.proxyAndHookTest;

import android.util.Log;

public class Cat implements Animal{
    @Override
    public void eat() {
        Log.i("ldld","cat 实现了eat方法");
    }

    @Override
    public void sleep() {
        Log.i("ldld","cat 实现了sleep方法");
    }
}
