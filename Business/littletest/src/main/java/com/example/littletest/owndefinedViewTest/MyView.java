package com.example.littletest.owndefinedViewTest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
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
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        if(widthMode == MeasureSpec.UNSPECIFIED){
            Log.d("ldld","width的类型是 UNSPECIFIED" );
        }else if(widthMode == MeasureSpec.AT_MOST){
            Log.d("ldld","width的类型是 AT_MOST" );  //wrap_content???
        }else if(widthMode == MeasureSpec.EXACTLY){
            Log.d("ldld","width的类型是 EXACTLY" ); //match_parent???
        }
        int widthData = MeasureSpec.getSize(widthMeasureSpec);
        int heightData = MeasureSpec.getSize(heightMeasureSpec);
        Log.d("ldld","这里 获取的是 父view width的数据是 :"+ widthData );

        setMeasuredDimension(widthData/3,heightData/3);
    }

    @Override
    public void layout(int l, int t, int r, int b) {
        Log.i("ldld","自定义view的layout l:" + l);
        Log.i("ldld","自定义view的layout t:" + t); //因为上面有个textview
        Log.i("ldld","自定义view的layout r:" + r);
        Log.i("ldld","自定义view的layout b:" + b);
        super.layout(l, t + 80, r, b);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setARGB(255,180,180,180);
        canvas.drawCircle(150,150,150,paint);
        super.onDraw(canvas);
    }

}
