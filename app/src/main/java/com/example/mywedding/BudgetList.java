package com.example.mywedding;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class BudgetList extends AppCompatActivity {
    FloatingActionButton btn_add;
    Button nav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget_list);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.app_name_budgetList);

        btn_add = findViewById(R.id.btn_add_budget);
        nav = findViewById(R.id.btn_nav);
    }

    @Override
    protected void onResume() {
        super.onResume();
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BudgetList.this,AddBudget.class);
                startActivity(intent);
            }
        });

        nav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BudgetList.this,ViewBudget.class);
                startActivity(intent);
            }
        });
    }
}