package com.example.littletest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.littletest.IntentServiceTest.IntentServiceTestActivity;
import com.example.littletest.IntentServiceTest.MyIntentService;
import com.example.littletest.okhttptest.OkHttpTestActivity;
import com.example.littletest.owndefinedViewTest.OwnDefinedViewActivity;
import com.example.littletest.producerComsumer.ProducerConsumerActivity;
import com.example.littletest.proxyAndHookTest.ProxyAndHookActivity;
import com.example.littletest.puretest.PureActivity;
import com.example.littletest.recycleAddandClear.RecycleViewActivity;
import com.example.littletest.recycleviewTest.RecycleViewTestActivity;
import com.example.littletest.setmaplistqueue.SetMapListQueueActivity;
import com.example.littletest.sortTest.SortTestActivity;
import com.example.littletest.synchonizedTest.SynchonizedActivity;
import com.example.littletest.threadPoolTest.ThreadPoolActivity;
import com.example.littletest.threadTest.ThreadTestActivity;

public class LittleActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_little);

        //recycleview
        //自定义view
        //set、map、list.   还有一个queue

        //---------------------

        //mvp可以把平安的代码找出来看看

        //okhttp
        //Parcelable与Serializable

        //touchview的设置
        //Bitmap
        //动态代理与hook..
        //ThreadPoolExecutor的各种test..
        //atomicInteger

        findViewById(R.id.pureTest).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(LittleActivity.this, PureActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.newTask).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(LittleActivity.this,TaskActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.recycleview).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(LittleActivity.this, RecycleViewActivity.class);
                startActivity(intent);
            }
        });

            findViewById(R.id.threadTest).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(LittleActivity.this, ThreadTestActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.sqlTest).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(LittleActivity.this,SQLTestActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.producer_consumer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(LittleActivity.this, ProducerConsumerActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.synchonizedTest).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(LittleActivity.this, SynchonizedActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.owndefinedView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(LittleActivity.this, OwnDefinedViewActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.test_recycleview).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(LittleActivity.this, RecycleViewTestActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.test_set_map_list_queue).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(LittleActivity.this, SetMapListQueueActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.test_proxy_and_hook).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(LittleActivity.this, ProxyAndHookActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.test_okhttp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(LittleActivity.this, OkHttpTestActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.test_thread_pool).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(LittleActivity.this, ThreadPoolActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.test_sort).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(LittleActivity.this, SortTestActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.intent_service_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(LittleActivity.this, IntentServiceTestActivity.class);
                startActivity(intent);
            }
        });




    }
}
