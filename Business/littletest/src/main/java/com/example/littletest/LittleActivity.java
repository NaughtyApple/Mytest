package com.example.littletest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.littletest.owndefinedViewTest.OwnDefinedViewActivity;
import com.example.littletest.producerComsumer.ProducerConsumerActivity;
import com.example.littletest.puretest.PureActivity;
import com.example.littletest.synchonizedTest.SynchonizedActivity;

import java.util.concurrent.ThreadPoolExecutor;

public class LittleActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_little);

        //mvp可以把平安的代码找出来看看

        //自定义view

        //recycleview

        //set、map、list.
        //okhttp
        //Parcelable与Serializable

        //touchview的设置
        //Bitmap
        //动态代理与hook..
        //ThreadPoolExecutor的各种test..


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

        findViewById(R.id.newTask).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(LittleActivity.this,RecycleViewActivity.class);
                startActivity(intent);
            }
        });

            findViewById(R.id.threadTest).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(LittleActivity.this,ThreadTestActivity.class);
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



    }
}
