package com.example.mywedding;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.mywedding.Database.DBHelper;

public class MainActivity extends AppCompatActivity {
    Button btn_register;

    //EditText etguestname,etgender,etnotes,etphone,etaddress,etemail;
    //,etstatus.getText().toString()  etstatus
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_register = findViewById(R.id.btn_registration);

        //RadioGroup myRadioGroup = (RadioGroup) findViewById(R.id.radioGroup2);
    }

    @Override
    protected void onResume() {
        super.onResume();
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Registration.class);
                startActivity(intent);
            }
        });
    }


}