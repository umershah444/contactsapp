package com.example.umer.mycallerapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper{


    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS contacts(name VARCHAR,number VARCHAR)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    void insertData(String name,String number){
        SQLiteDatabase sqLiteDatabase=getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",name);
        contentValues.put("number",number);
        sqLiteDatabase.insert("contacts",null,contentValues);
    }

    List<MyContacts> getData(){
        SQLiteDatabase sqLiteDatabase=getReadableDatabase();
        List<MyContacts> list=new ArrayList<MyContacts>();
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * FROM contacts",null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast())
        {
            MyContacts myContacts=new MyContacts();
            myContacts.setName(cursor.getString(cursor.getColumnIndex("name")));
            myContacts.setPhone(cursor.getString(cursor.getColumnIndex("number")));
            list.add(myContacts);
            cursor.moveToNext();
        }
        return list;
    }

}
