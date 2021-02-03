package com.example.littletest.producerComsumer;

import android.util.Log;

import java.util.Random;
import java.util.concurrent.LinkedBlockingDeque;

public class ConSumer implements  Runnable{

    private LinkedBlockingDeque<Goods> deque = new LinkedBlockingDeque<Goods>(10);

    ConSumer(LinkedBlockingDeque<Goods> param){
        deque = param;
    }

    @Override
    public void run() {
        double random = Math.random();
        long number = (long)(random*10);
        try {
            Thread.sleep(number*1000);
        } catch (InterruptedException e) {
            Log.i("ldld","休眠catch"+ e.toString() );
            e.printStackTrace();
        }
        deque.poll();
        Log.i("ldld","消费者消费了一个goods :" + deque.size());
    }
}
