package com.example.littletest.producerComsumer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.littletest.R;

import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingDeque;

public class ProducerConsumerActivity extends AppCompatActivity {

    private LinkedBlockingDeque<Goods> deque = new LinkedBlockingDeque<Goods>(10);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producer_consumer);

        findViewById(R.id.produce).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                producerStart();
            }
        });

        findViewById(R.id.consumer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                consumerStart();
            }
        });

        findViewById(R.id.toast).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });


    }

    public void producerStart(){
        for(int i = 0; i< 20; i++){
            Producer producer = new Producer(deque);
//            producer.run();

            Thread thread = new Thread(producer);
            thread.start();
        }
    }

    public void consumerStart(){
        for(int i = 0; i< 20; i++){
            ConSumer conSumer = new ConSumer(deque);
//            conSumer.run();

            //Runnable被thread包裹着，然后start，还有这么个事儿.
            Thread thread = new Thread(conSumer);
            thread.start();
        }
    }

}
