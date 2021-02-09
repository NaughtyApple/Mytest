package com.example.littletest.recycleviewTest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.littletest.R;

public class RecycleViewTestActivity extends AppCompatActivity {

    //创建步骤
    // 1、添加一个recycleview
    // 2、set一个layoutmanager
    // 3、set一个adapter，adpter中有需要复写的viewholder

    //holder的view 是在adpter中创建的，可以从布局中inflat出来..
    public int creatViewNum = 1;
    public int bindNum = 1;

    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_recycle_view_test);

        initView();
    }

    //viewholder 顾名思义，hold 住一个 view，view 从哪里来的，是flat出来的。。
    //这里应该有复用吧...
    public class InspectViewHolder extends RecyclerView.ViewHolder {
        public View innerView ;
        public InspectViewHolder(@NonNull View itemView) {
            super(itemView);
            innerView = itemView;
        }

        public void setNum(int num){
            TextView textView =  (TextView)innerView.findViewById(R.id.itemNum);
            textView.setText(String.valueOf(num)); //不能set整型哈
        }
    }

    public void initView(){
        RecyclerView recyclerView =  findViewById(R.id.recycleviewCode);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new RecyclerView.Adapter<InspectViewHolder>() {
            @NonNull
            @Override
            public InspectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(RecycleViewTestActivity.this).inflate(R.layout.recycle_item_inspect_code, null);
                Log.i("ldld","onCreateViewHolder的执行次数:" + (creatViewNum ++ ) );
                InspectViewHolder inspectViewHolder = new InspectViewHolder(view);
                return inspectViewHolder;
            }

            @Override
            public void onBindViewHolder(@NonNull InspectViewHolder holder, int position) {
//                Log.i("ldld","onBindViewHolder的执行次数:" + (bindNum ++ ) );
                holder.setNum(position+1);
            }

            @Override
            public int getItemCount() {
                return 100;
            }
        });
    }
}
