package com.example.littletest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class RecycleViewActivity extends Activity {

    public ArrayList<String> mData = new ArrayList<String>();
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_view);
        mData.add("第1个");
        mData.add("第2个");
        mData.add("第3个");
        mData.add("第4个");
        mData.add("第5个");

        view_flat = LayoutInflater.from(this).inflate(R.layout.recycle_item_linerlayout, (ViewGroup) findViewById(R.id.recycleview_container),false);

        findViewById(R.id.addData).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mData.add("第6个");
                recyclerView.getAdapter().notifyDataSetChanged();
            }
        });

        findViewById(R.id.clearData).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mData.clear();
                recyclerView.getAdapter().notifyDataSetChanged();
            }
        });


        recyclerView = findViewById(R.id.recycleview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new RecyclerView.Adapter<MyHolder>() {
            @NonNull
            @Override
            public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                ViewGroup view = (ViewGroup) LayoutInflater.from(RecycleViewActivity.this).inflate(R.layout.recycle_item_linerlayout, parent,false);
                MyHolder myHolder = new MyHolder(view);
                return myHolder;
            }

            @Override
            public void onBindViewHolder(MyHolder holder, int position) {
                holder.setItemWord(String.valueOf(mData.get(position)));
            }

            @Override
            public int getItemCount() {
                return mData.size();
            }
        });

    }

    public class MyHolder extends RecyclerView.ViewHolder{
        public View viewholder;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            viewholder = itemView;
        }
        public void setItemWord(String str){
            if(viewholder.findViewById(R.id.itemName) != null){
                ((TextView)viewholder.findViewById(R.id.itemName)).setText(str);
            }
        }
    }

     View view_flat;

}
