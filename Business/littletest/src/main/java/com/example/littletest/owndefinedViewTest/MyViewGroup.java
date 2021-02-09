package com.example.littletest.owndefinedViewTest;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

public class MyViewGroup extends ViewGroup {

    public MyViewGroup(Context context) {
        super(context);
    }

    public MyViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyViewGroup(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        //changed代表 是否变化过的意思...
        //传进来的参数是 viewgroup可以绘制的矩形区域表示...
        int count  = getChildCount();
        Log.i("ldld","获取到了几个子view: "+ count);
        Log.i("ldld","viewgroup获取到的值 l: "+ l +",t:"+ t +",r:"+ r +",b:"+ b);

        for(int i = 0; i<count ;i++){
            View view = getChildAt(i);
            view.layout(l,t + i * 90,r,b);
        }
    }
}
