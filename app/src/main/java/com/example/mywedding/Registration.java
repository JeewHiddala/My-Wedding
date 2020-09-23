package com.example.mywedding;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.mywedding.Database.DBHelper;

import java.util.Calendar;

public class Registration extends AppCompatActivity {
    private Button btn_register;
    private EditText et_selectDate, et_uName, et_uMail, et_uContact, et_pName, et_pMail, et_pContact, et_wedName;
    private RadioGroup userGroup, partnerGroup;
    private RadioButton radio1, radio2;
    private DatePickerDialog datePickerDialog;
    private DBHelper dbHelper = new DBHelper(this);
    private int selectedId1, selectedId2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.app_name_registration);


        btn_register = findViewById(R.id.btn_update);
        et_selectDate = findViewById(R.id.et_wedDate);
        et_uName = findViewById(R.id.et_userName);
        et_uMail = findViewById(R.id.et_userEmail);
        et_uContact = findViewById(R.id.et_userContact);
        et_pName = findViewById(R.id.et_pName);
        et_pMail = findViewById(R.id.et_pEmail);
        et_pContact = findViewById(R.id.et_pContact);
        et_wedName = findViewById(R.id.et_wedName);
        userGroup = findViewById(R.id.radUserStatus);
        partnerGroup = findViewById(R.id.radPStatus);

        et_selectDate.setInputType(InputType.TYPE_NULL);

        //Calendar view
        et_selectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                //date picker dialog box
                datePickerDialog = new DatePickerDialog(Registration.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                et_selectDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                datePickerDialog.show();
            }
        });

        //check application is opened for the first time
        SharedPreferences preferences = getSharedPreferences("PREFERENCE", MODE_PRIVATE);

        String firstTime = preferences.getString("FirstTimeInstall","");

        if(firstTime.equals("Yes")){
            //If application opened for the first time
            Intent intent = new Intent(Registration.this, Home.class);
            startActivity(intent);
        } else {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("FirstTimeInstall","Yes");
            editor.apply();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //getting selected value from user radio group
                selectedId1 = userGroup.getCheckedRadioButtonId();
                radio1 = findViewById(selectedId1);

                //getting selected value from partner radio group
                selectedId2 = partnerGroup.getCheckedRadioButtonId();
                radio2 = findViewById(selectedId2);


                if(TextUtils.isEmpty(et_selectDate.getText())){
                    Toast.makeText(Registration.this, "Please enter wedding date", Toast.LENGTH_SHORT).show();
                } else if(TextUtils.isEmpty(et_uName.getText()) || TextUtils.isEmpty(et_pName.getText())){
                    Toast.makeText(Registration.this, "Please check the names", Toast.LENGTH_SHORT).show();
                } else {
                    long val = dbHelper.addUser(et_uName.getText().toString(), et_uMail.getText().toString(), et_uContact.getText().toString(), radio1.getText().toString(), et_pName.getText().toString(), et_pMail.getText().toString(), et_pContact.getText().toString(), radio2.getText().toString(), et_wedName.getText().toString(), et_selectDate.getText().toString());

                    if (val > 0) {
                        Toast.makeText(Registration.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Registration.this, Home.class));
                    } else {
                        Toast.makeText(Registration.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }

}