package com.example.mywedding;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.EditText;
import android.view.View.OnTouchListener;
import android.app.DatePickerDialog;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.DatePicker;
import java.util.Calendar;
import android.content.Context;
import android.view.MenuInflater;
import android.content.Intent;
import androidx.annotation.NonNull;

public class AddTask extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    ImageButton btnDatePicker;
    EditText txtDate;
    private int mYear, mMonth, mDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.header_add_task_name);

        // Spinner element
        Spinner spinner = (Spinner) findViewById(R.id.editTaskCategory);

        // Spinner click listener
        spinner.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("Select");
        categories.add("Clothing and Accessories");
        categories.add("Decorations");
        categories.add("Health and Beauty");
        categories.add("Food and Beverages");
        categories.add("Transport");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);

        //date picker
        btnDatePicker=(ImageButton)findViewById(R.id.imageButton2);
        txtDate=(EditText)findViewById(R.id.editDate);

        btnDatePicker.setOnClickListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_save,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.done){
            Intent intent = new Intent(AddTask.this,TaskList.class);
            startActivity(intent);

            Context context = getApplicationContext();
            CharSequence message = "Task Added";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, message, duration);
            toast.show();
        }

        if(id == android.R.id.home){
            Intent intent = new Intent(AddTask.this, TaskList.class);
            startActivity(intent);
        }
        return true;
    }

    @Override
    public void onClick(View v) {

        // Get Current Date
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        txtDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

}