package com.example.littletest.owndefinedViewTest;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
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
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.i("ldld","自定义MyViewGroup onMeasure");

        int count  = getChildCount();

        for(int i = 0; i<count ;i++){
            View view = getChildAt(i);
//            view.measure(widthMeasureSpec,heightMeasureSpec);
            measureChildren(widthMeasureSpec,heightMeasureSpec);
//            measureChild(view,widthMeasureSpec,heightMeasureSpec);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        Log.i("ldld","自定义MyViewGroup onLayout");

        //changed代表 是否变化过的意思...
        //传进来的参数是 viewgroup可以绘制的矩形区域表示...
        int count  = getChildCount();
//        Log.i("ldld","获取到了几个子view: "+ count);
//        Log.i("ldld","viewgroup获取到的值 l: "+ l +",t:"+ t +",r:"+ r +",b:"+ b);

        for(int i = 0; i<count ;i++){
            View view = getChildAt(i);
            view.layout(l,t + i * 10,r/2,b/2);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.i("ldld","自定义MyViewGroup onDraw");

    }

    //下面是触摸事件相关的...
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i("ldld","自定义MyViewGroup onTouchEvent");
        return super.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.i("ldld","自定义MyViewGroup dispatchTouchEvent");
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.i("ldld","自定义MyViewGroup onInterceptTouchEvent");
        return super.onInterceptTouchEvent(ev);
    }

}
