package com.example.mywedding;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Home extends AppCompatActivity {
    ImageButton btn_tasks, btn_budget,btn_vendor,btn_guest, btn_dashboard, btn_settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //referring button ids
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

            //OnClickListener for tasks button
            btn_tasks.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Home.this,TaskList.class);
                    startActivity(intent);
                }
            });

            //OnClickListener for budget button
            btn_budget.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Home.this,BudgetList.class);
                    startActivity(intent);
                }
            });

            //OnClickListener for vendor button
            btn_vendor.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Home.this,VenderList.class);
                    startActivity(intent);
                }
            });

            //OnClickListener for guests button
            btn_guest.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Home.this,allGuests.class);
                    startActivity(intent);
                }
            });

            //OnClickListener for dashboard button
            btn_dashboard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Home.this,dashBoard.class);
                    startActivity(intent);
                }
            });

            //OnClickListener for settings button
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


            //To disable the back button from the home page
    @Override
    public void onBackPressed() {
        Toast.makeText(this, "Tap Home Button to Exit", Toast.LENGTH_SHORT).show();
    }

}
