package com.example.mywedding;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class ViewVender extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_vender);

        //Back Button in android
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}