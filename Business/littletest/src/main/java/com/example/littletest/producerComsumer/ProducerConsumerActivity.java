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
    private ArrayList<Producer> arrayProjucer = new ArrayList<Producer> (20);
    private ArrayList<ConSumer> arrayConsumer = new ArrayList<ConSumer> (20);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        for(int i = 0; i< 20; i++){
            arrayProjucer.add(new Producer(deque));
        }
        for(int i = 0; i< 20; i++){
            arrayConsumer.add(new ConSumer(deque));
        }

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

    }

    public void producerStart(){
        for(int i = 0; i< 20; i++){
            Thread thread = new Thread(arrayProjucer.get(i));
            thread.start();
        }
    }

    public void consumerStart(){
        for(int i = 0; i< 20; i++){
            //Runnable被thread包裹着，然后start，还有这么个事儿.
            Thread thread = new Thread(arrayConsumer.get(i));
            thread.start();
        }
    }

}
