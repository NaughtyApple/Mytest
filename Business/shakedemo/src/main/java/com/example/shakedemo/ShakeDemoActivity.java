package com.example.shakedemo;

import android.app.Activity;
import android.content.Context;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.baseactivity.BaseActivity;

public class ShakeDemoActivity extends BaseActivity {

    static int mShaking = 0; // shake状态位
    static boolean mShakingInit = false; // shake状态位

    private static StepShakeListener mStepshakeListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addContent(R.layout.activity_shake_demo);
        setTitle("摇动测试");

        StepShakeListener listener = new StepShakeListener();
        SensorManager sm = (SensorManager) this.getSystemService(Context.SENSOR_SERVICE);
        sm.registerListener(
                listener,
                sm.getDefaultSensor(android.hardware.Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_FASTEST);
        mStepshakeListener = listener;
        Log.d("ldld","register shakelistener");
    }

    private class StepShakeListener implements SensorEventListener {
        public void shake() {
            mShaking = 1;
            mShakingInit =true;
            ((TextView)findViewById(R.id.textView)).setText("检测到摇动");
            Log.d("ldld","detect shake");
        }
        public void shakeend() {
            mShaking = 3;
            ((TextView)findViewById(R.id.textView)).setText("未检测到摇动");
            Log.d("ldld","shaking end");
        }
        private long lastTime;
        private float last_x;
        private float last_y;
        private float last_z;
        private float totalShake;
        private int cnt = 0;

        private static final int TOTAL_THRESHOLD = 180;

        @Override
        public void onAccuracyChanged(android.hardware.Sensor sensor, int accuracy) {

        }

        public void onSensorChanged(SensorEvent event) {
            if (event.sensor.getType() == android.hardware.Sensor.TYPE_ACCELEROMETER) {
                // 获取加速度传感器的三个参数
                float x = event.values[SensorManager.DATA_X];
                float y = event.values[SensorManager.DATA_Y];
                float z = event.values[SensorManager.DATA_Z];
                // 获取当前时刻的毫秒数
                long curTime = System.currentTimeMillis();
                long duration = curTime - lastTime;
                if (duration > 5000) {
                    initShake(curTime);
                } else if (duration > 80) {
                    float shake = 0;
                    if (last_x != 0.0f || last_y != 0.0f || last_z != 0.0f) {
                        shake = Math.abs(x - last_x) + Math.abs(y - last_y) + Math.abs(z - last_z);
                    }

                    // 把每次的晃动幅度相加，得到总体晃动幅度
                    totalShake += shake;

                    if (totalShake > TOTAL_THRESHOLD && cnt >= 3) {
                        shake();
                        initShake(curTime);
                    }else if (cnt < 10) {
                        cnt++;
                        last_x = x;
                        last_y = y;
                        last_z = z;
                        lastTime = curTime;
                    } else {
                        initShake(curTime);
                        if(mShaking<3) {
                            shakeend();
                        }
                    }
                }
            }
        };

        private void initShake(long curTime) {
            lastTime = curTime;
            last_x = 0.0f;
            last_y = 0.0f;
            last_z = 0.0f;
            totalShake = 0.0f;
            cnt = 0;
        }

    }

}
