package com.example.sqliteapp;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;
import android.widget.Toast;

public class Display extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        TextView retriveText=findViewById(R.id.retrive_text);
        retriveText.setMovementMethod(new ScrollingMovementMethod());
        try{
            MyDBHelper db=new MyDBHelper(this);
            Cursor cursor=db.retriveAll();
            cursor.moveToFirst();
            retriveText.setText(" Roll No "+" "+" Name             "+" "+"Marks  ");
            do{
                retriveText.setText(retriveText.getText()+
                        "\n"+
                        cursor.getString(cursor.getColumnIndex("rno"))+"        "+
                        cursor.getString(cursor.getColumnIndex("name"))+"   "+
                        cursor.getString(cursor.getColumnIndex("mrk")));
            }while(cursor.moveToNext());

        }
        catch(Exception e){
            displayMsg("Data is not present in Database");
        }
    }
    public void displayMsg(String s){
        Context context =getApplicationContext();
        int duration= Toast.LENGTH_SHORT;

        Toast toast= Toast.makeText(context,s,duration);
        toast.show();
    }
}

