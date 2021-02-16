package com.example.littletest.threadPoolTest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.littletest.R;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class ThreadPoolActivity extends AppCompatActivity {

    private ArrayList<PoolRunner> arrayList = new ArrayList<PoolRunner>();

    //ArrayBlockingQueue才有固定的大小...
//    BlockingQueue<Runnable> blockingQueue = new LinkedBlockingDeque<Runnable>();
    BlockingQueue<Runnable> blockingQueue = new ArrayBlockingQueue<Runnable>(5);
    ThreadPoolExecutor threadPoolExecutor;

    public class PoolRunner implements Runnable{

        private String name;

        public PoolRunner(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            double random = Math.random();
            long number = (long)(random*10);
            Log.i("ldld","runnable name："+ name + " 准备睡眠一会.."+ number*1000);
            try {
                Thread.sleep(number*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Log.i("ldld","runnable name："+ name + "退出....." );
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_pool);

        int corePoolSize = 5;
        int maximumPoolSize = 5;
        long keepAliveTime = 30*1000;
//        TimeUnit timeUnit = new TimeUnit();

        //直接起线程运行任务...??性能损失比较大..
//        RejectedExecutionHandler handler = new ThreadPoolExecutor.CallerRunsPolicy();

        //抛出异常结束....(构造函数里 没有丢弃策略，默认是走这个策略..)
        //        RejectedExecutionHandler handler = new ThreadPoolExecutor.AbortPolicy();

        //直接丢弃..
        //RejectedExecutionHandler handler = new ThreadPoolExecutor.DiscardPolicy();

        //把工作队列里的挤掉..
        RejectedExecutionHandler handler = new ThreadPoolExecutor.DiscardOldestPolicy();

        threadPoolExecutor = new ThreadPoolExecutor( corePoolSize, maximumPoolSize,
                keepAliveTime, MILLISECONDS, blockingQueue, handler );

        for(int i = 0; i< 20; i++){
            arrayList.add(new PoolRunner(String.valueOf(i)));
        }
        for(int i = 0; i< 20; i++){
            threadPoolExecutor.execute(arrayList.get(i));
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i< 20; i++){
                    try {
                        Thread.sleep(2000);
                        Log.i("ldld","blockingQueue的 长度大小是多少: "+ blockingQueue.size() );
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        findViewById(R.id.add_callable).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Future<Boolean> result = threadPoolExecutor.submit(new Callable<Boolean>() {

                    @Override
                    public Boolean call() throws Exception {
                        try {
                            Thread.sleep(2000);
                            Log.i("ldld","Callable 执行，休眠两秒... " );
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        Boolean result = true;
                        Log.i("ldld","Callable 休眠结束 = =... " );
                        return result;
                    }
                });
                try {
                    Log.i("ldld","开始尝试获取Future的object... " );
                    Boolean s = result.get();
                    Log.i("ldld","获取到了Future的s.. " + s );
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        });

        findViewById(R.id.shut_down_threadpool).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                threadPoolExecutor.shutdown();
            }
        });

    }
}