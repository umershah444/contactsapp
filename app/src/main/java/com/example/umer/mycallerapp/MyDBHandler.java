package com.example.umer.mycallerapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.strictmode.SqliteObjectLeakedViolation;

public class MyDBHandler extends SQLiteOpenHelper {

    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE  IF NOT EXISTS MyContacts (name VARCHAR, phone VARCHAR)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    void addContact(String name, String phone)
    {
        SQLiteDatabase sqLiteDatabase=getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("name",name);
        contentValues.put("phone",phone);
        sqLiteDatabase.update("MyContacts",contentValues,"name=abc",null);
    }
    void getData()
    {
        SQLiteDatabase sqLiteDatabase=getWritableDatabase();
       Cursor cursor= sqLiteDatabase.query("MyContacts",null,null,null,null,null,null);
    }
}
