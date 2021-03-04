package com.example.littletest;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
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
//                sqLiteHelper.insert( "ld"+ (int)(Math.random()*100000),"男","18");
                String textid =  editText.getText().toString();
                long result = sqLiteHelper.insert( "ld"+ textid,"男","18"); //这个返回结果居然是有多少条记录?
                Log.i("ldld","插入结果是:" + result);
            }
        });

        findViewById(R.id.add_transaction).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = sqLiteHelper.getWritableDatabase();
                ContentValues cv = new ContentValues();
//                cv.put("Name", "ld1");
                cv.put("Sex", "男");
                cv.put("Age", 1888);
                cv.put("Age2", "1822");
                ContentValues cv2 = new ContentValues();
                cv2.put("Name", "ld99999999");
                cv2.put("Sex", "男");
                cv2.put("Age", "18");

                db.beginTransaction();
                try{
                    //这里如果是 db.insert的话 返回 -1 ，则不会走catch，得是执行sql语句的命令才行...
                    //android.database.sqlite.SQLiteConstraintException: UNIQUE constraint failed: student.Name (Sqlite code 1555 SQLITE_CONSTRAINT_PRIMARYKEY), (OS error - 11:Try again)
                    long row = db.insert(TABLE_NAME, null, cv);
                    long row2 = db.insert(TABLE_NAME, null, cv2);
                    for(int i  = 0; i< 100; i++){
                        long temp  = sqLiteHelper.insert( "ld"+ i,"男","18");
                        Log.i("ldld","插入结果....准备回滚:"+ temp);
                    }
                    db.execSQL("insert into student(Name, Sex, Age) values(?,?,?)", new Object[]{"ld", "男","19"});
//                    db.execSQL("insert into student(Name, Sex, Age) values(?,?,?)", new Object[]{"ld333", "男","19"});

                    Log.i("ldld","row..." + row );
                    Log.i("ldld","row2..." + row2 );
                    db.setTransactionSuccessful();
                    Log.i("ldld","事务处理 正常结束..." );
                }
                catch(Exception e){
                    Log.i("ldld","事务处理出现异常:" + e.toString());
                }
                finally{
                    //结束事务
                    Log.i("ldld","事务处理 finally执行..." );
                    db.endTransaction();
                    db.close(); //?
                }
            }
        });


        findViewById(R.id.add_all_sql_data).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for(int i  = 0; i< 10000; i++){
                            sqLiteHelper.insert( "ld"+ i,"男","18");
                        }
                    }
                }).start();
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
