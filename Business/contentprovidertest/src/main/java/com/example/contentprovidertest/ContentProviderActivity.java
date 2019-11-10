package com.example.contentprovidertest;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.baseactivity.BaseActivity;

public class ContentProviderActivity extends BaseActivity {

    public String TAG = "ContentProviderActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("contentProvider测试");
        addContent(R.layout.activity_content_provider);

        findViewById(R.id.contentprovidertest).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insert();
            }
        });

        findViewById(R.id.contentprovidertest_query).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                query();
            }
        });

    }

    public void insert(){
        ContentResolver contentResolver = this.getContentResolver();
        Uri uri = Uri.parse("content://com.example.contentprovidertest.UserProvider/user");
        ContentValues values = new ContentValues();
        values.put("name", "mark");
        values.put("address","上海");
        Uri uri2 = contentResolver.insert(uri, values);
        Log.i(TAG, uri2.toString());
    }

    public void query(){
        ContentResolver contentResolver = this.getContentResolver();
        Uri uri = Uri.parse("content://com.example.contentprovidertest.UserProvider/user");
        Cursor cursor = contentResolver.query(uri, null, null, null, null);
        int columns = cursor.getColumnCount();
        while(cursor.moveToNext()){
            for(int i = 0 ; i < columns ; i++){
                String col_name = cursor.getColumnName(i);
                String col_value = cursor.getString(cursor.getColumnIndex(col_name));
                Log.i(TAG, "--------------->" + col_name);
                Log.i(TAG, "--------------->" + col_value);
            }
        }
    }
    public void updata(){
        ContentResolver contentResolver = this.getContentResolver();
        Uri uri = Uri.parse("content://com.example.contentprovidertest.UserProvider/user/1");
        ContentValues values = new ContentValues();
        values.put("name", "lucy");
        int row = contentResolver.update(uri, values, null, null);
        Log.i(TAG, "**********************************"+row);
    }

    public void delete(){
        ContentResolver contentResolver = this.getContentResolver();
        Uri uri = Uri.parse("content://com.example.contentprovidertest.UserProvider/user/2");
        int row = contentResolver.delete(uri, null,null);
        Log.i(TAG, "^^^^^^^^^^^^^^^^^^^^^^^^^^^^^" + row);
    }

}
