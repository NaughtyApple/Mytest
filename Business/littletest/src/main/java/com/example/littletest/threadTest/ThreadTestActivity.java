package com.example.littletest.threadTest;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.MessageQueue;
import android.util.Log;
import android.view.View;

import com.example.littletest.R;

public class ThreadTestActivity extends Activity {

    public volatile int num = 0;

    public Thread thread1;
    public Thread thread2;
    public Thread thread3;
    public Thread thread4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_test);

        thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                Log.i("ldld","thread1 run 执行起来....." );
                try {
                    Thread.sleep(8000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.i("ldld","thread1 run 执行结束....." );
            }
        });

        thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                //这个join的用法是 thread1 在执行中的话，就会等thread1 先执行完。
                try {
                    thread1.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.i("ldld","thread2 run 执行起来....." );
                try {
                    Thread.sleep(8000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.i("ldld","thread2 run 执行结束....." );
            }
        });

        thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                Log.i("ldld","thread3 run 执行起来....." );
                try {
                    Log.i("ldld","thread3 执行一下yield....." );
                    Thread.yield();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Log.i("ldld","thread3 run 执行结束....." );
            }
        });

        thread3.start();
        try {
            thread3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Log.i("ldld","主线程 执行结束..." );

//        thread4 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                Log.i("ldld","thread4 run 执行起来....." );
//                for (int i = 0; i< 10; i ++){
//                    new Thread(new Runnable() {
//                        @Override
//                        public void run() {
//                            for (int j = 0; j < 1000; j++) {
////                                num ++ ;
//                                addnum();
//                            }
//                        }
//                    }).start();
//                }
//            }
//        });

        findViewById(R.id.thread1_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thread1.start();
            }
        });

        findViewById(R.id.thread2_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thread2.start();
            }
        });

        findViewById(R.id.check_num).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("ldld","最后的num的大小是.....:"+ num );
            }
        });

        findViewById(R.id.thread4_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i< 50000; i ++){
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            addnum();
                        }
                    }).start();
                }
            }
        });

    }

    public void addnum(){
        num ++ ;
    }
}
