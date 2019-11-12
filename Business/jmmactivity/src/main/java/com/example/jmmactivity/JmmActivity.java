package com.example.jmmactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.baseactivity.BaseActivity;

public class JmmActivity extends BaseActivity {

    boolean flag = true;
    volatile boolean volatileFlag = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("JMM内存屏障测试");
        addContent(R.layout.activity_jmm);

        new Thread(new Runnable() {
            @Override
            public void run() {
                flag = false;
                while(true){
                    Log.i("ldld","thread on flag:"+flag);
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                volatileFlag = false;
                while (true){
                    Log.i("ldld","volatile thread on volatileFlag:"+volatileFlag);
                }
            }
        }).start();

//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        flag = false;
//        volatileFlag = false;

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                flag = false;
                Log.i("ldld2","thread flag:"+flag);
            }
        });

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                volatileFlag = false;
                Log.i("ldld2","volatile volatileFlag:"+volatileFlag);
            }
        });

    }

}
