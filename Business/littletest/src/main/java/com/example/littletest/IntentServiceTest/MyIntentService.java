package com.example.littletest.IntentServiceTest;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

public class MyIntentService extends IntentService {

    private String myname;

    //IntentService 也是 Service需要注册，还需要一个默认构造函数?

    public MyIntentService() {
        super("default");
        myname = "defaut";
    }

    public MyIntentService(String name) {
        super(name);
        myname = name;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("ldld",">>>onCreate");
    }

    @Override
    public void onDestroy() {
        Log.i("ldld",">>>onDestroy");
        super.onDestroy();
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Log.i("ldld","休眠catch"+ e.toString() );
            e.printStackTrace();
        }
        Log.i("ldld",myname + " 执行了: "+ intent.getStringExtra("param") );
    }
}
