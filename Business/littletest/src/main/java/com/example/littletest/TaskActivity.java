package com.example.littletest;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;


// Caused by: java.lang.IllegalStateException: You need to use a Theme.AppCompat theme (or descendant) with this activity.
//AppCompatActivity

//adb -s xxx shell
//dumpsys activity activities | sed -En -e '/Running activities/,/Run #0/p'
//以上命令查看activity record

// 需要设置不同的taskAffinity，newtask才可以生效

public class TaskActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
    }
}
