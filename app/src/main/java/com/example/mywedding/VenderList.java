package com.example.mywedding;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class VenderList extends AppCompatActivity {

    FloatingActionButton fabadd;
    Button btnView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vender_list);

        //Back Button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Floating Action Button
        fabadd = (FloatingActionButton) findViewById(R.id.fabtnaddjh);

        //View Button
        btnView = (Button) findViewById(R.id.btn_hoteldjh);
    }

    @Override
    protected void onResume() {
        super.onResume();

        fabadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VenderList.this, AddVender.class);
                startActivity(intent);
            }
        });

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VenderList.this, ViewVender.class);
                startActivity(intent);
            }
        });
    }
}