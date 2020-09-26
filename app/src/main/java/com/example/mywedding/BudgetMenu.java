package com.example.mywedding;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class BudgetMenu extends AppCompatActivity {
    Button btn_budget_list, btn_paid_list, btn_settings;
    ImageButton btn_all, btn_paid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget_menu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.app_name_Budget);


        btn_paid = findViewById(R.id.imgbtn_paid);
        btn_all = findViewById(R.id.imgbtn_all);
        btn_budget_list = findViewById(R.id.btn_budget);
        btn_paid_list = findViewById(R.id.btn_paid);
//        btn_settings = findViewById(R.id.btn_settings);
    }

    @Override
    protected void onResume() {
        super.onResume();
        btn_budget_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BudgetMenu.this,BudgetList.class);
                startActivity(intent);
            }
        });

        btn_paid_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BudgetMenu.this,PaidList.class);
                startActivity(intent);
            }
        });

        btn_paid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BudgetMenu.this,PaidList.class);
                startActivity(intent);
            }
        });

        btn_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BudgetMenu.this,BudgetList.class);
                startActivity(intent);
            }
        });
/*        btn_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BudgetMenu.this,Settings.class);
                startActivity(intent);
            }
        });*/
    }
}