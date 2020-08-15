package com.example.mywedding;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class CompleteTask extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_task);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.header_complete_tasklist_name);
    }
}