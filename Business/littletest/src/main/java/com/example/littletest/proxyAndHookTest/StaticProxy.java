package com.example.littletest.proxyAndHookTest;

import android.util.Log;

public class StaticProxy implements Animal{

    private Animal animal;

    StaticProxy(Animal a){
        animal = a;
    }

    @Override
    public void eat() {
        Log.i("ldld","静态代理 eat");
        animal.eat();
    }

    @Override
    public void sleep() {
        Log.i("ldld","静态代理 sleep");
        animal.sleep();
    }

}
