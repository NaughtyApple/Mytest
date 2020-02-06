package com.example.pluginactivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.example.pluginactivity.clipboard.ClipHelper;
import com.example.pluginactivity.myproxy.myProxy;
import com.example.pluginactivity.myproxy.myProxyHandler;
import com.example.pluginactivity.myproxy.myProxyInterface;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apk);

        findViewById(R.id.hook_clipboard).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    ClipHelper.binder();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        findViewById(R.id.hook_mytest).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //被代理对象
                myProxyInterface myself = new myProxy();

                //与切面的业务结合
                InvocationHandler myhandler = new myProxyHandler(myself);

                //生成我们自己的代理对象,来实现别的切面业务
                myProxyInterface myProxyObject = (myProxyInterface) Proxy.newProxyInstance(myself.getClass().getClassLoader(), myself.getClass().getInterfaces(),myhandler);
                myProxyObject.myproxyinfo();

            }
        });

        findViewById(R.id.open_apk).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });




    }

}
