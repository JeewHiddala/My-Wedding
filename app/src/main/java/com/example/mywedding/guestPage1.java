package com.example.mywedding;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

public class guestPage1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_page1);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.guestpageadd);
    }


    public void allguest(View view){
        Intent intent = new Intent(guestPage1.this,allGuests.class);
        startActivity(intent);
    }

    public void uninvited(View view){
        Intent intent = new Intent(guestPage1.this,Uninvitedlist.class);
        startActivity(intent);


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();


        if(id == android.R.id.home){
            Intent intent = new Intent(guestPage1.this,Home.class);
            startActivity(intent);
        }

        return true;
    }
}