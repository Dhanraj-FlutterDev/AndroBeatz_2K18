package com.example.firstapplication;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView text;
    int r = 0, g = 0, b = 0;
    float size;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = findViewById(R.id.textView);
    }

    public void increment(View view) {
        size = text.getTextSize();
        if (size <= 150) {
            text.setTextSize(TypedValue.COMPLEX_UNIT_PX, size + 4);
        }
    }

    public void decrement(View view) {
        size = text.getTextSize();
        if (size >= 20) {
            text.setTextSize(TypedValue.COMPLEX_UNIT_PX, size - 4);
        }
    }


    public void changeColor(View view){
        text.setTextColor(Color.rgb(r,g,b));
        r+=15;
        g+=30;
        b+=45;
    }
}