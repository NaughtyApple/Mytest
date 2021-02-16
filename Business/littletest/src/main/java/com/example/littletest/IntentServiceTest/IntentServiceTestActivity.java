package com.example.littletest.IntentServiceTest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.littletest.R;

public class IntentServiceTestActivity extends AppCompatActivity {

    private MyIntentService myIntentService = new MyIntentService("my intent service ");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_service_test);

        findViewById(R.id.exe_intent_service).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("ldld","onclick to intent sercice ...");
                Intent intent = new Intent();
                intent.putExtra("param",String.valueOf(Math.random()) );
                intent.setClass(IntentServiceTestActivity.this,MyIntentService.class);

                startService(intent);

            }
        });
    }
}