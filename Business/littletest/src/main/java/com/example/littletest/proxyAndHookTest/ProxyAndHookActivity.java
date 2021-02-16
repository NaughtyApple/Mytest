package com.example.littletest.proxyAndHookTest;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Instrumentation;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.littletest.LittleActivity;
import com.example.littletest.R;
import com.example.littletest.puretest.PureActivity;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyAndHookActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proxy_and_hook);

        //静待代理
        Cat cat = new Cat();
        Animal staticAnimal = new StaticProxy(cat);
        //调用代理对象
        staticAnimal.eat();

        //动态代理
        Animal dynamicAnimal = (Animal)Proxy.newProxyInstance(Animal.class.getClassLoader(),
                cat.getClass().getInterfaces(),
                new DynamicProxy(cat)
                );
        dynamicAnimal.eat();


        Class<?> actManagerNativeCls = null;
        try {
            actManagerNativeCls = Class.forName("android.app.ActivityManager");
            //获取gDefault
            Field gDefaultField = actManagerNativeCls.getDeclaredField("IActivityManagerSingleton");
            gDefaultField.setAccessible(true);
            Object gDefaultObj = gDefaultField.get(null);

            Class<?> singleton = Class.forName("android.util.Singleton");
            Field mInstanceField = singleton.getDeclaredField("mInstance");
            mInstanceField.setAccessible(true);

            Object activityImpl = mInstanceField.get(gDefaultObj);

            Object actProxy = Proxy.newProxyInstance(activityImpl.getClass().getClassLoader(),
                    activityImpl.getClass().getInterfaces(),new DynamicProxy(activityImpl));
            mInstanceField.set(gDefaultObj,actProxy);// 构造一个动态代理的对象(动态这里一定是一个接口????)，并把他赋值回去..

//            Class<?> activityThreadCls = Class.forName("android.app.ActivityThread");
//            //1.获取ActivityThread对象
//            //hook点，有public的方法或属性，优先
//            Method currentActThreadMethod = activityThreadCls.getDeclaredMethod("currentActivityThread");
//            Object curThreadObj = currentActThreadMethod.invoke(null);
//            //获取mInstrumentation
//            Field instrumentationField = curThreadObj.getClass().getDeclaredField("mInstrumentation");
//            instrumentationField.setAccessible(true);
//            instrumentationField.set(curThreadObj,new InstrumentationProxy((Instrumentation) instrumentationField.get(curThreadObj)));

            Log.i("ldld","动态hook set finish..." );

        } catch (Exception e) {
            Log.i("ldld","动态hook遇到了一些问题:"+ e.toString());
            e.printStackTrace();
        }



        findViewById(R.id.hook_start_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(ProxyAndHookActivity.this, PureActivity.class);
                startActivity(intent);
            }
        });

    }
}