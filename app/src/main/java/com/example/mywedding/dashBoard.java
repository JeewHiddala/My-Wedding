package com.example.mywedding;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class dashBoard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.dboard);
    }
}