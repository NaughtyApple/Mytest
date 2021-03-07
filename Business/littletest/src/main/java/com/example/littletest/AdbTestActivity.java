package com.example.littletest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import java.io.IOException;
import java.io.OutputStream;

public class AdbTestActivity extends AppCompatActivity {

    public static Handler myHandler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adb_test);

        final String cmd = "input swipe 100 500 600 500 \n";
        final String cmd2 = "input keyevent 3 \n";

        Log.i("ldld","adb 尝试执行....");
        myHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    OutputStream os = Runtime.getRuntime().exec("su").getOutputStream();
                    os.write(cmd2.getBytes());
                    os.flush();
                    Log.i("ldld","cmd2 执行完毕");
                } catch (IOException e){
                    Log.i("ldld","cmd2 出错:"+ e.toString());
                    e.printStackTrace();
                }
        }},5000);
        myHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    OutputStream os = Runtime.getRuntime().exec("su").getOutputStream();
                    os.write(cmd.getBytes());
                    os.flush();
                    Log.i("ldld","cmd1 执行完毕");
                } catch (IOException e){
                    Log.i("ldld","cmd1 出错:"+ e.toString());
                    e.printStackTrace();
                }
            }},10000);
    }
}