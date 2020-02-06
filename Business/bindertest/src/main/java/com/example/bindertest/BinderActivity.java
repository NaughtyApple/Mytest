package com.example.bindertest;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class BinderActivity extends Activity {

    IMyAidlInterface iMyAidlInterface;
    private ServiceConnection conn  = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.i("ldld","service onServiceConnected");
            iMyAidlInterface = IMyAidlInterface.Stub.asInterface(iBinder);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.i("ldld","service onServiceDisconnected");
            iMyAidlInterface = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binder);

        //服务端
        //1、添加aidl文件
        //2、build一次后生成 generated java 文件
        //3、根据ibinder文件，在service中实现binder接口中的函数
        //4、启动该service

        findViewById(R.id.start_server).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(new Intent(BinderActivity.this,MyService.class));
            }
        });



        //客户端
        //1、仅需要一步，即需要service的bind使用方法，需要bindservice.

        findViewById(R.id.start_bind).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(BinderActivity.this, MyService.class);
                //下面这种写法好像不行就是
                //intent.setComponent(new ComponentName("com.example.bindertest","com.example.bindertest.MyService"));
                bindService(intent, conn,BIND_AUTO_CREATE);
            }
        });

        findViewById(R.id.start_client).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    String result = iMyAidlInterface.getServerInfo();
                    Toast.makeText(BinderActivity.this,result,Toast.LENGTH_SHORT).show();
                } catch (RemoteException e) {
                    Log.i("ldld",e.toString());
                    e.printStackTrace();
                }

            }
        });


    }
}
