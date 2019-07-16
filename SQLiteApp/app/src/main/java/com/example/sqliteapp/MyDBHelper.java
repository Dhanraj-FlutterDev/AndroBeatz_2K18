package com.example.sqliteapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME="myStu.db";
    private static final String TABLE_NAME="student";
    private static final String COLOUMN_RNO="rno";
    private static final String COLOUMN_NAME="name";
    private static final String COLOUMN_MARKS="mrk";

    MyDBHelper(Context c){
        super(c,DB_NAME,null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        String query="CREATE TABLE "+TABLE_NAME+
                " ( "+COLOUMN_RNO+" INTEGER PRIMARY KEY, "
                +COLOUMN_NAME+" VARCHAR(30), "
                +COLOUMN_MARKS+" REAL );";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean add(int rno,String name,double mrk){
        ContentValues values=new ContentValues();
        values.put(COLOUMN_RNO,rno);
        values.put(COLOUMN_NAME,name);
        values.put(COLOUMN_MARKS,mrk);

        SQLiteDatabase db=getWritableDatabase();
        try{
            db.insert(TABLE_NAME,null,values);
            db.close();
        }
        catch (Exception e){
            db.close();
            return false;
        }
        return true;
    }
    public Cursor retriveAll(){
        SQLiteDatabase db=getWritableDatabase();
        Cursor cr=db.rawQuery("SELECT * FROM "+TABLE_NAME,null);
        return cr;
    }
}
