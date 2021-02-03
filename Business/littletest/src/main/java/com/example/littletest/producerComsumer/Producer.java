package com.example.littletest.producerComsumer;


import android.util.Log;

import java.util.concurrent.LinkedBlockingDeque;

public class Producer implements Runnable {

    private LinkedBlockingDeque<Goods> deque = new LinkedBlockingDeque<Goods>(10);

    Producer(LinkedBlockingDeque<Goods> param){
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

        Goods goods = new Goods(0);
        deque.add(goods);
        Log.i("ldld","生产者添加了一个goods数量:" + deque.size());
    }

}
