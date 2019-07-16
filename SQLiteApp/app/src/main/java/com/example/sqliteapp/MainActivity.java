package com.example.sqliteapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText text1;
    EditText text2;
    EditText text3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text1=findViewById(R.id.editText_roll);
        text2=findViewById(R.id.editText_name);
        text3=findViewById(R.id.editText_marks);
    }
    public void onAddClick(View view){
        MyDBHelper db=new MyDBHelper(this);
        try{
            if(db.add(Integer.parseInt(text1.getText().toString()),text2.getText().toString(),
                    Double.parseDouble(text3.getText().toString())))
            {
                displayMsg("New Data Inserted");
            }
        }
        catch (Exception e){
            displayMsg(e.toString());
        }
        text1.setText("");
        text2.setText("");
        text3.setText("");

    }
    public void onDisplayClick(View view){
        Intent displaying =new Intent(this,Display.class);
        startActivity(displaying);

    }
    public void displayMsg(String s){
        Context context =getApplicationContext();
        int duration= Toast.LENGTH_SHORT;

        Toast toast= Toast.makeText(context,s,duration);
        toast.show();
    }
}
