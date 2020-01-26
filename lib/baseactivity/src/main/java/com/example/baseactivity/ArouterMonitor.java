package com.example.baseactivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

public class ArouterMonitor {

    public static Activity mContext;

    public volatile static ArouterMonitor instance; //静态重要，volatil更重要
    public static ArouterMonitor getInstance(Activity context){
        mContext = context;
        if(instance == null) {
            synchronized (ArouterMonitor.class){ //这里是ArouterMonitor.class，非ArouterMonitor.this
                if(instance == null) {   //双重锁定说的是 判null
                    instance = new ArouterMonitor();
                }
            }
        }
        return instance;
    }

    public String getMemoryString() {
        return memoryString;
    }

    public void setMemoryString(String memoryString) {
        this.memoryString = memoryString;
    }

    public String memoryString ="这是还没初始化的memoryString";

    public void startShakeActivity(){
        try {
            Class<?> shakeClass = Class.forName("com.example.shakedemo.ShakeDemoActivity");
            shakeClass.newInstance();
            Intent intent = new Intent();
            intent.setClass(mContext,shakeClass);
            mContext.startActivity(intent);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
