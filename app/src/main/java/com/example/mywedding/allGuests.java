package com.example.mywedding;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class allGuests extends AppCompatActivity {

    //Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_guests);

        //btn1 = findViewById(R.id.btn_glist1);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.allguests);
    }

    public void addGuest(View view){
        Intent intent = new Intent(allGuests.this,addGuest.class);
        startActivity(intent);
    }
    public void viewGuest(View view){
        Intent intent = new Intent(allGuests.this,viewGuest.class);
        startActivity(intent);
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();


        if(id == android.R.id.home){
            Intent intent = new Intent(allGuests.this,guestPage1.class);
            startActivity(intent);
        }

        return true;
    }

}