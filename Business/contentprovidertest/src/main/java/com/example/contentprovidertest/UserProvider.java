package com.example.contentprovidertest;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import com.example.baseactivity.DBHelper;

public class UserProvider extends ContentProvider {

    private DBHelper helper;
    private static final UriMatcher URI_MATCHER = new UriMatcher(
            UriMatcher.NO_MATCH);
    private static final int USER = 1;// 操作单条记录
    private static final int USERS = 2;// 操作多条记录

    static {// 添加匹配的URI，分别是单条记录和多条记录
        URI_MATCHER.addURI("com.example.contentprovidertest.UserProvider", "user/#", USER);
        URI_MATCHER.addURI("com.example.contentprovidertest.UserProvider", "user", USERS);
    }

    @Override
    public boolean onCreate() {
        // TODO Auto-generated method stub
        helper = new DBHelper(getContext());
        return true;
    }

    //UserProvider在manifest的exported设置为true之后
    //其他程序也可以利用query这段代码查询到值
    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        // TODO Auto-generated method stub
        Cursor cursor = null;
        SQLiteDatabase database = helper.getReadableDatabase();
        int flag = URI_MATCHER.match(uri);
        try {
            switch (flag) {
                case USER:
                    long id = ContentUris.parseId(uri);
                    String where_value = " id = " + id;
                    if (selection != null && !"".equals(selection)) {
                        where_value += " and " + selection;
                    }
                    cursor = database.query("user", null, where_value,
                            selectionArgs, null, null, null);
                    break;
                case USERS:
                    cursor = database.query("user", null, selection, selectionArgs,
                            null, null, null);
                default:
                    break;
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return cursor;
    }

    @Override
    public String getType(Uri uri) {
        // TODO Auto-generated method stub
        int match = URI_MATCHER.match(uri);
        switch (match) {
            case USER:// 如果匹配的是单条记录
                return "vnd.android.cursor.item/user";

            case USERS:// 如果匹配的是多条记录
                return "vnd.android.cursor.dir/users";
        }
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // TODO Auto-generated method stub
        Uri resultUri = null;
        SQLiteDatabase database = helper.getWritableDatabase();
        int flag = URI_MATCHER.match(uri);
        long id = 0;
        switch (flag) {
            case USERS:
                id = database.insert("user", null, values);
                resultUri = ContentUris.withAppendedId(uri, id);
                break;
            case USER:
                id = database.insert("user", null, values);
                String path = uri.toString();
                resultUri = Uri
                        .parse(path.substring(0, path.lastIndexOf("/")) + id);
            default:
                break;
        }
        return resultUri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // TODO Auto-generated method stub
        SQLiteDatabase database = helper.getWritableDatabase();
        int flag = URI_MATCHER.match(uri);
        long id = -1;
        int count = 0;
        switch (flag) {
            case USER:
                id = ContentUris.parseId(uri);
                String where_value = " id = " + id;
                if (selection != null && !selection.equals("")) {
                    where_value += " and ";
                }
                count = database.delete("user", where_value, selectionArgs);
                break;
            case USERS:
                count = database.delete("user", selection, selectionArgs);
                break;
            default:
                break;
        }
        return count;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO Auto-generated method stub
        SQLiteDatabase database = helper.getWritableDatabase();
        int flag = URI_MATCHER.match(uri);
        long id;
        int count = 0;
        switch (flag) {
            case USER:
                id = ContentUris.parseId(uri);
                String where_value = " id = " + id;
                if (selection != null && !selection.equals("")) {
                    where_value += " and ";
                }
                count = database.update("user", values, where_value, selectionArgs);
                break;
            case USERS:
                count = database.update("user", values, selection, selectionArgs);
            default:
                break;
        }
        return count;
    }
}