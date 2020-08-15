package com.example.mywedding;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class allGuests extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_guests);
    }

    public void addGuest(View view){
        Intent intent = new Intent(allGuests.this,addGuest.class);
        startActivity(intent);
    }

}