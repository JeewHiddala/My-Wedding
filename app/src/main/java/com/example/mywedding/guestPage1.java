package com.example.mywedding;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class guestPage1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_page1);
        getSupportActionBar().setTitle(R.string.guestpage1);
    }


    public void allguest(View view){
        Intent intent = new Intent(guestPage1.this,allGuests.class);
        startActivity(intent);
    }

    public void uninvited(View view){
        Intent intent = new Intent(guestPage1.this,Uninvitedlist.class);
        startActivity(intent);


    }

}