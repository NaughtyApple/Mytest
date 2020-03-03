package com.example.jnitest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thirdappmodule.ThirdModuleActivity;

public class JniActivity extends AppCompatActivity {

    //基本得cpp调用
    //声明native方法，添加system.loadLibrary
    //建立cpp文件夹，建立对应c文件，然后加上cmakelist文件(有一定的编写)，点击src文件夹进行link，gradle会自动添加编译配置
    //即可以完成调用了
    //调用之后会在build/immedertiate/x86/xxxx.so 会生成的so
    //so放到libs文件下，配置sourceset，修改cmakelist文件处理掉c文件的引用..

    public TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jni);
        textView = findViewById(R.id.text);
        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = stringFromJNI();
                textView.setText(str);
            }
        });


        //直接跳转aar，可以访问
//        Intent intent = new Intent();
//        intent.setClass(this, ThirdModuleActivity.class);
//        startActivity(intent);

    }

    /* A native method that is implemented by the
     * 'hello-jni' native library, which is packaged
     * with this application.
     */

    //这里这个命名是有讲究的，得对应上啊
    public native String  stringFromJNI();

    /* This is another native method declaration that is *not*
     * implemented by 'hello-jni'. This is simply to show that
     * you can declare as many native methods in your Java code
     * as you want, their implementation is searched in the
     * currently loaded native libraries only the first time
     * you call them.
     *
     * Trying to call this function will result in a
     * java.lang.UnsatisfiedLinkError exception !
     */
    public native String  unimplementedStringFromJNI();

    /* this is used to load the 'hello-jni' library on application
     * startup. The library has already been unpacked into
     * /data/data/com.example.hellojni/lib/libhello-jni.so at
     * installation time by the package manager.
     */

    //下面这个很重要，写cpp也会生成
    static {
        System.loadLibrary("hello-jni");
    }

}
