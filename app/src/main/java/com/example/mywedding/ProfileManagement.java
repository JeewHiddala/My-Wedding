package com.example.mywedding;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.InputType;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.mywedding.Database.DBHelper;
import com.example.mywedding.Models.UserModel;

import java.util.Calendar;

public class ProfileManagement extends AppCompatActivity {
    private Button btn_update;
    private EditText etUname, etUmail, etUcontact, etPartname, etPartmail, etPartcontact, etWedName, etWedDate;
    private DatePickerDialog datePickerDialog;
    private RadioButton userrad1, userrad2, partrad1, partrad2, radioButton1, radioButton2;
    private RadioGroup userGroup, partnerGroup;
    private DBHelper dbHelper;
    private String selectedRadio1, selectedRadio2;
    private int selectedId1, selectedId2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_management);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.app_name_profileManagement);

        dbHelper = new DBHelper(this);

        int id = 1;

        //fetching values to the userModel object
        UserModel userModel = dbHelper.getUserDetails(id);

        btn_update = findViewById(R.id.btn_update);
        etUname = findViewById(R.id.userName);
        etUmail = findViewById(R.id.userEmail);
        etUcontact = findViewById(R.id.userContact);
        etPartname = findViewById(R.id.pName);
        etPartmail = findViewById(R.id.pEmail);
        etPartcontact = findViewById(R.id.pContact);
        etWedName = findViewById(R.id.wedName);
        userGroup = findViewById(R.id.radGroup1);
        userrad1 = findViewById(R.id.radBride1);
        userrad2 = findViewById(R.id.radGroom1);
        partnerGroup = findViewById(R.id.radGroup2);
        partrad1 = findViewById(R.id.radBride2);
        partrad2 = findViewById(R.id.radGroom2);


        etWedDate = findViewById(R.id.wedDate);
        etWedDate.setInputType(InputType.TYPE_NULL);

        //Calendar view
        etWedDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                //date picker dialog box
                datePickerDialog = new DatePickerDialog(ProfileManagement.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                etWedDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                datePickerDialog.show();
            }
        });

        //printing values to the edit text fields
        etUname.setText(userModel.getUserName());
        etUmail.setText(userModel.getUserEmail());
        etUcontact.setText(userModel.getUserContact());
        etPartname.setText(userModel.getPartnerName());
        etPartmail.setText(userModel.getPartnerEmail());
        etPartcontact.setText(userModel.getPartnerContact());
        etWedName.setText(userModel.getWeddingName());
        etWedDate.setText(userModel.getWeddingDate());

        //getting selected radio value of user
        selectedRadio1 = userModel.getUserStatus();

        //displaying the radio value of the user
        if(selectedRadio1.equals("Bride")){
            userrad1.setChecked(true);
        } else {
            userrad2.setChecked(true);
        }

        //getting selected radio value of partner
        selectedRadio2 = userModel.getPartnerStatus();

        //displaying the radio value of the partner
        if(selectedRadio2.equals("Bride")){
            partrad1.setChecked(true);
        } else {
            partrad2.setChecked(true);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //getting the selected user value in the view
                selectedId1 = userGroup.getCheckedRadioButtonId();
                radioButton1 = findViewById(selectedId1);

                //getting the selected partner value in the view
                selectedId2 = partnerGroup.getCheckedRadioButtonId();
                radioButton2 = findViewById(selectedId2);

                String uName = etUname.getText().toString();
                String uMail = etUmail.getText().toString();
                String uContact = etUcontact.getText().toString();
                String pName = etPartname.getText().toString();
                String pMail = etPartmail.getText().toString();
                String pContact = etPartcontact.getText().toString();
                String wName = etWedName.getText().toString();
                String wDate = etWedDate.getText().toString();

                //checking input fields
                if(TextUtils.isEmpty(etUname.getText()) || TextUtils.isEmpty(etPartname.getText())){
                    Toast.makeText(ProfileManagement.this, "You cannot proceed without name", Toast.LENGTH_SHORT).show();
                } else if(TextUtils.isEmpty(etWedDate.getText())){
                    Toast.makeText(ProfileManagement.this, "Date is compulsory", Toast.LENGTH_SHORT).show();
                } else {
                    UserModel user = new UserModel(1, uName, uMail, uContact, radioButton1.getText().toString(), pName, pMail, pContact, radioButton2.getText().toString(), wName, wDate);

                    //invoking the update function
                    long val = dbHelper.updateUser(user); //if the entry passed successfully, it pass a long type value greater than 0

                    if (val > 0) {
                        Toast.makeText(ProfileManagement.this, "Updated Successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ProfileManagement.this, Settings.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(ProfileManagement.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }
}