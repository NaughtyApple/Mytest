package com.example.littletest.owndefinedViewTest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.example.littletest.R;

public class OwnDefinedViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_own_defined_view);
        final MyView myView = findViewById(R.id.inner_myview);
        final MyViewGroup myViewGroup = findViewById(R.id.outer_view);
        final LinearLayout linearLayout = findViewById(R.id.outest_view);

        findViewById(R.id.toinvalidate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myView.invalidate();
            }
        });

        findViewById(R.id.to_request_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myView.requestLayout();
            }
        });

        findViewById(R.id.inner_myview).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                float pt = event.getX();
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        Log.i("ldld","自定义MyView 的ACTION_DOWN事件...");
                    break;
                    case MotionEvent.ACTION_MOVE:
//                        Log.i("ldld","自定义MyView 的ACTION_MOVE事件...");
                        break;
                    case MotionEvent.ACTION_UP:
                        Log.i("ldld","自定义MyView 的ACTION_UP事件...");
                        break;
                }

                //决定view的ontouchevent会不会顺利执行到...
//                return false;
                return true;

            }
        });

        findViewById(R.id.inner_myview).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("ldld","inner_myview 的 click事件 ... ...");
            }
        });
        findViewById(R.id.inner_myview).setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Log.i("ldld","inner_myview 的 long_click事件 ... ...");
                return false;
            }
        });

    }
}
