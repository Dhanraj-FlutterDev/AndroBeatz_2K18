package com.example.firebase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class DisplayData extends AppCompatActivity {
    TextView javamark,dsmark;
    private DatabaseReference databaseReference;

    FirebaseUser user;
    private FirebaseAuth auth;
    String Java,DS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_data);
        javamark = findViewById(R.id.java);
        dsmark = findViewById(R.id.ds);
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
    }


    public  void showData(View view) {

        databaseReference.child(user.getUid()).child("Java").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Java = dataSnapshot.getValue().toString();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        databaseReference.child(user.getUid()).child("DS").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                DS = dataSnapshot.getValue().toString();
                Toast.makeText(getApplicationContext(), DS, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        javamark.setText("Java Mark:" + Java);
        dsmark.setText("DS Mark:" + DS);
    }

}

