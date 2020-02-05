package com.example.littletest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class SQLiteHelper extends SQLiteOpenHelper {

    private Context mContext;

    public final static String DATABASE_NAME = "database_student";
    public final static int DATABASE_VERSION = 1;
    public final static String TABLE_NAME = "student";

    //创建数据库，里面添加了3个参数，分别是：Msgone VARCHAR类型，30长度当然这了可以自定义
    //Msgtwo VARCHAR(20)   Msgthree VARCHAR(30))  NOT NULL不能为空
    String sql = "CREATE TABLE " + TABLE_NAME
            + "(_id INTEGER ,"
            + " Name VARCHAR(30)  PRIMARY KEY,"
            + " Sex VARCHAR(20),"
            + " Age VARCHAR(30))";

    public SQLiteHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i("ldld","SQLiteHelper onCreate");
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //插入一条记录
    public long insert(String msg1,String msg2,String msg3 ) {
        Log.i("ldld","SQLiteHelper insert");
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("Name", msg1);
        cv.put("Sex", msg2);
        cv.put("Age", msg3);
        long row = db.insert(TABLE_NAME, null, cv);
        return row;
    }

    public void deleteTable(String table)
    {
        Log.i("ldld","SQLiteHelper insert");
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + table);
        db.close();
    }

    //删除记录
    public void delete(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String where ="Name = ?";
        String[] whereValue = { id };
        db.delete(TABLE_NAME, where, whereValue);
    }

    //根据条件查询
    public Cursor query(String[] args) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE Name LIKE ?", args);

         ArrayList<String> list = new ArrayList<String>();
         //执行查询语句
        //遍历游标
         while (cursor.moveToNext()){
          String name=cursor.getString(cursor.getColumnIndex("Name"));
          list.add(name);
         }

        Toast.makeText(mContext,String.valueOf(list.size()),Toast.LENGTH_SHORT).show();

        return cursor;
    }

    //更新记录,把对应name的年纪改成19
    public void update(String id ) {
        SQLiteDatabase db = this.getWritableDatabase();
        String where = "Name = ?";
        String[] whereValue = { id };
        ContentValues cv = new ContentValues();
        cv.put("Age", "19");
        db.update(TABLE_NAME, cv, where, whereValue);
    }


}
