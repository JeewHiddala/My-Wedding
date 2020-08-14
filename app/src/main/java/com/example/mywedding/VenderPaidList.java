package com.example.mywedding;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class VenderPaidList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vender_paid_list);

        //Back Button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}