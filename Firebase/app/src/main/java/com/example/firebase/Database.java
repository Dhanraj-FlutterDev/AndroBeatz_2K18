package com.example.firebase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Database extends AppCompatActivity {
    private TextView mark;
    private TextView javatext;
    private EditText javae;
    private TextView data;
    private EditText datae;
    private Button submit;
    private Button show;
    int a,b;
    String java,ds;
    FirebaseUser user;
    FirebaseAuth auth;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        mark=(TextView) findViewById(R.id.textviewmark);
        javatext=(TextView) findViewById(R.id.textviewjava);
        javae=(EditText) findViewById(R.id.editjava);
        data=(TextView) findViewById(R.id.textviewdata);
        datae=(EditText) findViewById(R.id.editdata);
        submit=(Button) findViewById(R.id.submit);
        show=(Button) findViewById(R.id.show);
        auth =FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        databaseReference= FirebaseDatabase.getInstance().getReference("subjects");
    }

    public void submitData(View view)
    {
        String a1 = javae.getText().toString();
        String a2= datae.getText().toString();

        if(!TextUtils.isEmpty(a1) && !TextUtils.isEmpty(a2))
        {
            databaseReference.child(user.getUid()).child("Java").setValue(a1);
            databaseReference.child(user.getUid()).child("DS").setValue(a2);
            Toast.makeText(getApplicationContext(),"Inserted",Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(this,"Enter all the marks",Toast.LENGTH_LONG).show();
        }

    }

    public  void showData(View view)
    {
        startActivity(new Intent(getApplicationContext(),DisplayData.class));
    }
}
