package com.example.littletest;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.MessageQueue;
import android.util.Log;
import android.view.View;

public class ThreadTestActivity extends Activity {

    public Thread thread1;
    public Thread thread2;

    public int totleNum = 70;
    public boolean thread1_flag = true;
    public boolean thread2_flag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_test);
        MessageQueue m;
        thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (thread1_flag){
                    Log.i("ldld","thread1 excute totalNum:" + totleNum);
                    totleNum --;
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if(totleNum <= 50){
                        thread1_flag = false;
                    }
                }
            }
        });

        thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if(thread1.isAlive()){
                        thread1.join();
                    }else {
                        thread1.start();
                        thread1.join();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                while (thread2_flag){
                    Log.i("ldld","thread2 excute totalNum:" + totleNum);
                    totleNum --;
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if(totleNum <= 30){
                        thread2_flag = false;
                    }
                }
            }
        });

        findViewById(R.id.thread1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thread1.start();
            }
        });

        findViewById(R.id.thread2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thread2.start();
            }
        });

    }
}
