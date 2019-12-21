package com.example.jmmactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.baseactivity.BaseActivity;

//个人结论：非volatile变量跨线程读写快速，但从子线程读取时机不受控，容易set false后还进入循环
//volatile变量则会慢一点，但是时序更稳定。。。因为他屏蔽了虚拟器中必要的代码优化，在必要时才使用此关键字

//这里写的线程都没有退出机制的。。
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
                while(flag){
                    Log.i("ldld","thread on flag:"+flag);
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (volatileFlag){
                    Log.i("ldld","volatile thread on volatileFlag:"+volatileFlag);
                }
            }
        }).start();

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag = false;
                Log.i("ldld2","thread flag:"+flag);
            }
        });

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                volatileFlag = false;
                Log.i("ldld2","volatile volatileFlag:"+volatileFlag);
            }
        });

    }

}
