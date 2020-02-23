package com.example.mytest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.FileUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bindertest.BinderActivity;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

//git仓库这里，来到文件夹下执行github的一系列命令就好了
//git add .
//git commit -a -m "message"
//git push
public class MainActivity extends Activity {
    private RecyclerView mRecyclerView;
    private ArrayList<TestItem> mTestItemArrays = new ArrayList<TestItem>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView)findViewById(R.id.recycleview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(new MyAdapter());

        //新增一个module，然后移动module到business目录，调整setting.gradle
        //然后调整module的build.gradle的 apply plugin的设置
        //app的build.gradle的编译 :business:module. (删除module的mainfest中的android:theme="@style/AppTheme"，有报错情况)
        //然后添加下面的item

        TestItem xposedItem = new TestItem("Xposed测试","com.example.xposedtest.XposedActivity");
        mTestItemArrays.add(xposedItem);

        //小测试采用组件化配置
        TestItem littleItem = new TestItem("小项目测试","com.example.littletest.LittleActivity");
        mTestItemArrays.add(littleItem);

        TestItem jniItem = new TestItem("Jni测试","com.example.jnitest.JniActivity");
        mTestItemArrays.add(jniItem);

        TestItem jmmItem = new TestItem("JMM线程变量测试","com.example.jmmactivity.JmmActivity");
        mTestItemArrays.add(jmmItem);
        TestItem contentProviderItem = new TestItem("ContentProvider测试","com.example.contentprovidertest.ContentProviderActivity");
        mTestItemArrays.add(contentProviderItem);
        TestItem binderItem = new TestItem("Binder测试","com.example.bindertest.BinderActivity");
        mTestItemArrays.add(binderItem);
        TestItem shakedemoItem = new TestItem("Shake测试","com.example.shakedemo.ShakeDemoActivity");
        mTestItemArrays.add(shakedemoItem);
        TestItem pluginActivityItem = new TestItem("插件化apk测试","com.example.pluginactivity.MainActivity");
        mTestItemArrays.add(pluginActivityItem);
        TestItem socketActivityItem = new TestItem("socket跨进程通信测试","com.example.sockettest.SocketActivity");
        mTestItemArrays.add(socketActivityItem);



    }

    class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.recycle_item, null);
            MyViewHolder myViewHolder = new MyViewHolder(view);
            return myViewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
            holder.setItemWord(mTestItemArrays.get(position).itemWord);
            holder.viewholder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent();
                    Class<?> clazz = null;
                    try {
                        clazz = Class.forName(mTestItemArrays.get(position).itemActivity);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    if(clazz == null){
                        Toast.makeText(MainActivity.this,"未找到目标module",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    intent.setClass(MainActivity.this, clazz);
                    startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return mTestItemArrays.size();
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        public View viewholder;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            viewholder = itemView;
        }
        public void setItemWord(String str){
            ((TextView)viewholder.findViewById(R.id.itemName)).setText(str);
        }
    }
}
