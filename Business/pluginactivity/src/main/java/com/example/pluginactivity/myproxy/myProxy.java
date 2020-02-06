package com.example.pluginactivity.myproxy;

import android.util.Log;

public class myProxy implements myProxyInterface {
    @Override
    public void myproxyinfo() {
        Log.i("ldld","这是我想要代理的接口方法");
    }
}
