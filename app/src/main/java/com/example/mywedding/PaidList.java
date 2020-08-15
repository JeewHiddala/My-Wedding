package com.example.mywedding;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class PaidList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paid_list);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.app_name_paidList);
    }
}