package com.example.littletest.producerComsumer;


import android.util.Log;

import java.util.concurrent.LinkedBlockingDeque;

public class Producer implements Runnable {

    private LinkedBlockingDeque<Goods> deque ;
    private Boolean flag = true;

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
        try {
            deque.put(goods);
        } catch (InterruptedException e) {
            e.printStackTrace();
            Log.i("ldld","阻塞队列put的时候出错 产生的问题:" + e.toString());

        }
        Log.i("ldld","生产者添加了一个goods数量:" + deque.size());

        Log.i("ldld","生产者者退出了.... ");


    }

}
