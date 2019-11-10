package com.example.baseactivity;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static String name = "mydb.db";
    private static int version = 1;

    public DBHelper(Context context) {
        super(context, name, null, version);
        // TODO Auto-generated constructor stub
    }
    @Override
    public void onCreate(SQLiteDatabase database) {
        // TODO Auto-generated method stub
        String sql = "create table user (id integer primary key autoincrement ,name varchar(64) ,address varchar(64))";
        database.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
    }

}
