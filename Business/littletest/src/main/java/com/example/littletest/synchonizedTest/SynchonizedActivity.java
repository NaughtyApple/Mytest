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

        findViewById(R.id.lock_lockinterruptly).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    thread1.start();
                    thread2.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        findViewById(R.id.interrupt_two).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    thread2.interrupt();

                    thread1.interrupt();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        findViewById(R.id.direct_lock).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    reentrantLock1.lock();
                    reentrantLock2.lock();
                } catch (Exception e) {
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

    //ReentrantLock的中断和非中断加锁模式的区别在于：线程尝试获取锁操作失败后，在等待过程中，如果该线程被其他线程中断了，它是如何响应中断请求的。
    // lock方法会忽略中断请求，继续获取锁直到成功；而lockInterruptibly则直接抛出中断异常来立即响应中断，由上层调用者处理中断。
    ReentrantLock reentrantLock1 = new ReentrantLock();
    ReentrantLock reentrantLock2 = new ReentrantLock();

    private Thread thread1 = new Thread(new Runnable() {
        @Override
        public void run() {
            Log.i("ldld","thread 1 跑起来");

            double a = 0;
            try {
                Log.i("ldld","thread 1 尝试获取锁");
                reentrantLock1.lock();
                for(int i = 0; i< 10000; i ++){
                    for(int j = 0; j< 1000; j ++){
                        for(int m = 0; m< 1000; m ++){
                            a ++;
                        }
                    }
                }
                Log.i("ldld","thread 1 执行完了..........");
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                reentrantLock1.unlock();
                Log.i("ldld","thread 1 释放锁....");
            }
            Log.i("ldld","thread 1 跑完了");
        }
    });

    private Thread thread2 = new Thread(new Runnable() {
        @Override
        public void run() {
            Log.i("ldld","thread 2 跑起来");
            int a = 0;
            try {
                Log.i("ldld","thread 2 尝试获取锁...");
                reentrantLock2.lockInterruptibly();
                for(int i = 0; i< 10000; i ++){
                    for(int j = 0; j< 1000; j ++){
                        for(int m = 0; m< 1000; m ++){
                            a ++;
                        }
                    }
                }
                Log.i("ldld","thread 2 执行完了..........");
            } catch (InterruptedException e) {
                Log.i("ldld","thread 2 就可以中断出来了");
                e.printStackTrace();
            }finally {
//                reentrantLock2.unlock();
                Log.i("ldld","thread 2 没获取到直接中断出来....");
                Log.i("ldld","thread 2 释放锁....");
            }
            Log.i("ldld","thread 2 跑完了...");
        }
    });

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
