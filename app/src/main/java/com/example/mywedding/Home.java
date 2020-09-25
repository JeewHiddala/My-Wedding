package com.example.mywedding;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Home extends AppCompatActivity {

    ImageButton btn_tasks, btn_budget,btn_vendor,btn_guest, btn_dashboard, btn_settings;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btn_tasks = findViewById(R.id.taskBtn1);
        btn_budget = findViewById(R.id.budgetBtn1);
        btn_vendor = findViewById(R.id.vendorsBtn1);
        btn_guest = findViewById(R.id.guestBtn1);
        btn_dashboard = findViewById(R.id.dashboardBtn1);
        btn_settings = findViewById(R.id.settingBTN);
    }


        @Override
        protected void onResume () {
            super.onResume();

            btn_tasks.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Home.this,TaskMenu.class);
                    startActivity(intent);
                }
            });

            btn_budget.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Home.this,BudgetMenu.class);
                    startActivity(intent);
                }
            });

            btn_vendor.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Home.this,VenderList.class);
                    startActivity(intent);
                }
            });

            btn_guest.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Home.this,guestPage1.class);
                    startActivity(intent);
                }
            });

            btn_dashboard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Home.this,dashBoard.class);
                    startActivity(intent);
                }
            });

            btn_settings.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Home.this,Settings.class);
                    startActivity(intent);
                }
            });

    }
            //tharuvi
            public void Dashboard(View view){
            Intent intent = new Intent(Home.this,dashBoard.class);
            startActivity(intent);
            }

}
