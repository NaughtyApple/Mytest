package com.example.baseactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class BaseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
    }

    public void setTitle(String title){
        ((TextView)findViewById(R.id.baseTitle)).setText(title);
    }


    public void addContent(int resource){
        View view = LayoutInflater.from(this).inflate(resource,null);
        ((ViewGroup)findViewById(R.id.baseContent)).addView(view);
    }
}
