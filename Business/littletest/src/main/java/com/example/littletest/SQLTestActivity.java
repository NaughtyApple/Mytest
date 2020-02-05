package com.example.littletest;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import static com.example.littletest.SQLiteHelper.TABLE_NAME;

public class SQLTestActivity extends Activity {

    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqltest);

        editText = findViewById(R.id.edit_student);

        final SQLiteHelper sqLiteHelper = new SQLiteHelper(this);

        findViewById(R.id.add_sql_data).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqLiteHelper.insert( editText.getText().toString(),"男","18");
            }
        });

        findViewById(R.id.delete_sql_data).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqLiteHelper.delete( editText.getText().toString());
            }
        });

        findViewById(R.id.delete_all).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqLiteHelper.deleteTable(TABLE_NAME);
            }
        });

        findViewById(R.id.query_student).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //模糊查询 咳咳要写通配符的，我以为like就完事了的
                String[] str = {editText.getText().toString()+"%"};
                sqLiteHelper.query( str );
            }
        });

        findViewById(R.id.change_data).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqLiteHelper.update( editText.getText().toString() );
            }
        });


    }
}
