package com.example.littletest.templateTest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.littletest.R;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class TemplateTestActivity extends AppCompatActivity {

//    @LdInfo(value = "dddddd", isDelete = false )
    class Box<T> {
        private T data;

        public Box() {

        }

        public Box(T data) {
            this.data = data;
        }

        public T getData() {
            Log.i("ldld","打印出 自定义泛型的T data:" + data);
            return data;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_template_test);

        Box box = new Box("自定义 template T");
        box.getData();
        Class aClass = box.getClass();

        //获取类中的方法
//        Annotation annotation = aClass.getAnnotation(LdInfo.class);
//        if(annotation != null){
//            Log.i("ldld","annotation annotation annotation:" + annotation.toString()  );
//        }


    }

}