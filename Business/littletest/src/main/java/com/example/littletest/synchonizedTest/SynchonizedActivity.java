package com.example.littletest.synchonizedTest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.littletest.R;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class SynchonizedActivity extends AppCompatActivity {

    private int sum = 0;
    private static int staticSum = 0;

    public void addStatic(){
        staticSum ++ ;
    }
    public synchronized void syncAddStatic(){
        staticSum ++ ;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_synchonized);

        initView();
    }

    public void initView(){
        findViewById(R.id.no_synchonized).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 这样直接会卡住哦 ～～～
                // 10万次，成员变量这里 出错的概率才会高起来....？？？这个结论好像有问题..
                for (int i = 0; i< 10000; i ++){
                     new Thread(new Runnable() {
                         @Override
                         public void run() {
                             sum ++ ;
                         }
                     }).start();
                 }
            }
        });

        findViewById(R.id.no_synchonized_static).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 静态变量的 add 更容易发生 同步问题.
                for (int i = 0; i< 10000; i ++){
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            addStatic();
                        }
                    }).start();
                }
            }
        });

        findViewById(R.id.synchonized_static).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i< 10000; i ++){
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            syncAddStatic();
                        }
                    }).start();
                }
            }
        });

        findViewById(R.id.checksum).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SynchonizedActivity.this,"最后的sum值:"+ sum, Toast.LENGTH_SHORT).show();
                Toast.makeText(SynchonizedActivity.this,"最后静态 sum值:"+ staticSum, Toast.LENGTH_SHORT).show();

            }
        });
        findViewById(R.id.clearsum).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SynchonizedActivity.this,"全部重新设置为0.", Toast.LENGTH_SHORT).show();
                sum = 0;
                staticSum = 0;
            }
        });
        //
        findViewById(R.id.thread1_in_thread2_wait).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i< 3; i ++){
                    final int finalI = i;
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                //内部变量调用外部变量的时候，为什么一定要设置成final ....?????
                                threadIn("线程 "+ String.valueOf(finalI));
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                }
            }
        });

        findViewById(R.id.lock_and_await).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                LockthreadIn("lock线程1");
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
            }
        });

        findViewById(R.id.siganl_it).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reentrantLock.lock();
                try {
                    //lock放在try里面就会报错,拿到锁之后才能进入try catch...
                    //reentrantLock.lock();
                    Log.i("ldld"," siganl_it siganl_it siganl_it ...");
                    condition.signal();
                } finally {
                    reentrantLock.unlock();
                }
            }
        });

        findViewById(R.id.third_process_wait).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (int i = 0; i< 3; i ++){
                    final int finalI = i;
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                threadWait("线程 "+ String.valueOf(finalI));
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                }

            }
        });
        findViewById(R.id.notify_one).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    threadNotifyOne();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        findViewById(R.id.notify_all).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    threadNotifyAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });


    }

    public void threadWait(String inword) throws InterruptedException {
        synchronized(this){
            Log.i("ldld", "this 是什么东西:"+ this.toString() );
            Log.i("ldld",inword +" 准备 进入wait..");
            this.wait();
            Log.i("ldld",inword +"休眠结束，准备退出");
        }
    }
    public void threadNotifyOne() throws InterruptedException {
        synchronized(this){
            Log.i("ldld", " 调用一次notify.");
            this.notify();
        }
    }
    public void threadNotifyAll() throws InterruptedException {
        synchronized(this){
            Log.i("ldld", " 调用notifyall.");
            this.notifyAll();
        }
    }

    ReentrantLock reentrantLock = new ReentrantLock();
    Condition condition = reentrantLock.newCondition();

    public void LockthreadIn(String inword) throws InterruptedException {
            Log.i("ldld",inword +" 准备进入同步代码块......");

            reentrantLock.lock();
            try {
                double random = Math.random();
                long number = (long)(random*1000);
                Log.i("ldld",inword +" lock 线程准备 释放锁.."+ number);
                condition.await();
            }catch (Exception e){
                Log.i("ldld",inword +" lock 线程 Exception.."+ e.toString() );
            }finally {
                reentrantLock.unlock();
                Log.i("ldld",inword +"lock 线程 释放锁...");
            }
            Log.i("ldld",inword +"lock 线程 准备退出 ...");

    }

    public void threadIn(String inword) throws InterruptedException {
        Log.i("ldld",inword +" 准备进入同步代码块......");
        synchronized(this){
            double random = Math.random();
            long number = (long)(random*1000);
            Log.i("ldld",inword +" 准备 休眠.."+ number);
            Thread.sleep(number);
            Log.i("ldld",inword +"休眠结束，准备退出");
        }

    }



}
