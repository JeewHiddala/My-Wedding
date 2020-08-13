package com.example.mywedding;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class BudgetMenu extends AppCompatActivity {
    Button btn_budget_list, btn_paid_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget_menu);

        btn_budget_list = findViewById(R.id.btn_budget);
        btn_paid_list = findViewById(R.id.btn_paid);
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
    }
}