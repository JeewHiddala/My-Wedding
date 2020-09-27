package com.example.mywedding;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mywedding.Database.DBHelper;
import com.example.mywedding.Database.UserMaster;
import com.example.mywedding.Models.UserModel;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Home extends AppCompatActivity {
    ImageButton btn_tasks, btn_budget,btn_vendor,btn_guest, btn_dashboard, btn_settings;
    TextView wedDatetxt, brideGroom, txtDays, txtHours, txtMinutes, txtSeconds;
    Handler handler;
    Runnable runnable;
    String weddingDate;
    private DBHelper dbHelper;

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

        //referring textviews
        wedDatetxt = findViewById(R.id.wedDate2);
        brideGroom = findViewById(R.id.brideName1);

        //Getting Intent data
        dbHelper = new DBHelper(this);


        int id = 1;
        UserModel userModel = dbHelper.getUserDetails(id);
        weddingDate = userModel.getWeddingDate();
        String userName = userModel.getUserName();
        String partnerName = userModel.getPartnerName();

        System.out.println(weddingDate);

        brideGroom.setText(userName + " & " + partnerName);
        wedDatetxt.setText(weddingDate);

        txtDays = findViewById(R.id.txtWeddingDays);
        txtHours = findViewById(R.id.txtWeddingHours);
        txtMinutes = findViewById(R.id.txtWeddingMins);
        txtSeconds = findViewById(R.id.txtWeddingSeconds);

        countDownStart();

    }

    //countDown function for wedding date
    public void countDownStart() {
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                handler.postDelayed(this, 1000);

                //using try catch exception for countdown fuction
                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat(
                            "dd/MM/yyyy");
                    // setting wedding date
                    Date futureDate = dateFormat.parse(weddingDate);
                    Date currentDate = new Date();
                    if (!currentDate.after(futureDate)) {
                        long diff = futureDate.getTime()
                                - currentDate.getTime();
                        long days = diff / (24 * 60 * 60 * 1000);
                        diff -= days * (24 * 60 * 60 * 1000);
                        long hours = diff / (60 * 60 * 1000);
                        diff -= hours * (60 * 60 * 1000);
                        long minutes = diff / (60 * 1000);
                        diff -= minutes * (60 * 1000);
                        long seconds = diff / 1000;
                        txtDays.setText("" + String.format("%02d", days));
                        txtHours.setText("" + String.format("%02d", hours));
                        txtMinutes.setText(""
                                + String.format("%02d", minutes));
                        txtSeconds.setText(""
                                + String.format("%02d", seconds));
                    } else {

                        //final Toast toast = Toast.makeText(Home.this, "Expired Date! CountDown is Over", Toast.LENGTH_SHORT);
                       // toast.setGravity(Gravity.TOP|Gravity.LEFT, 10, 660);
                       // toast.show();
                       // toast.setDuration(500);
                       // toast.cancel();

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        handler.postDelayed(runnable, 1 * 1000);
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
                    Intent intent = new Intent(Home.this,guestPage1.class);
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

    //To disable the back button from the home page
    @Override
    public void onBackPressed() {
        Toast.makeText(this, "Tap Home Button to Exit", Toast.LENGTH_SHORT).show();
    }

}
