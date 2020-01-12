package com.example.littletest;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LittleActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_little);

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
    }
}
