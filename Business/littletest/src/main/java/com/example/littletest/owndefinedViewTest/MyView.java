package com.example.littletest.owndefinedViewTest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class MyView extends View {
    //1、有四个构造函数..
    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    //2、重新定义3个方法...
//    UNSPECIFIED：父结点对子结点的大小没有任何要求。
//    EXACTLY:  父结点要求其子节点的大小指定为某个确切的值。其子节点以及其他子孙结点都需要适应该大小。
//    AT MOST：父结点要求其子节点的大小不能超过某个最大值，其子节点以及其他子孙结点的大小都需要小于这个值
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.i("ldld","自定义view onMeasure");

//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        if(widthMode == MeasureSpec.UNSPECIFIED){
//            Log.d("ldld","width的类型是 UNSPECIFIED" );
        }else if(widthMode == MeasureSpec.AT_MOST){
//            Log.d("ldld","width的类型是 AT_MOST" );
        }else if(widthMode == MeasureSpec.EXACTLY){
//            Log.d("ldld","width的类型是 EXACTLY" );
        }
        int widthData = MeasureSpec.getSize(widthMeasureSpec);
        int heightData = MeasureSpec.getSize(heightMeasureSpec);
//        Log.d("ldld","这里 获取的是 父view width的数据是 :"+ widthData );

        setMeasuredDimension(300,500);
    }

    @Override
    public void layout(int l, int t, int r, int b) {
        Log.i("ldld","自定义view layout");

//        Log.i("ldld","自定义view的layout l:" + l);
//        Log.i("ldld","自定义view的layout t:" + t); //因为上面有个textview
//        Log.i("ldld","自定义view的layout r:" + r);
//        Log.i("ldld","自定义view的layout b:" + b);
        super.layout(l, t + 20, r, b);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        Log.i("ldld","自定义view onLayout...执行归执行，不需要");
        super.onLayout(changed, left, top, right , bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.i("ldld","自定义view 执行了onDraw");
        Paint paint = new Paint();
        paint.setARGB(255,180,180,180);
        canvas.drawCircle(150,150,150,paint);
        super.onDraw(canvas);
    }

    //下面是触摸事件相关的...
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i("ldld","自定义MyView onTouchEvent");

        //这才是 整个的一连串
        //setontouch如果返回true ，则会拦截到住，不再会响应outouchevent，
        //ontouchevent的回调会触发  longclick 以及 click
        //longcick 的返回值 决定后续是不是click会触发
        return super.onTouchEvent(event);
//        return true;

    }

    //如果有touchlistener则touch ，touchevent后面...
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.i("ldld","自定义MyView dispatchTouchEvent");
        return super.dispatchTouchEvent(event);
    }


}
