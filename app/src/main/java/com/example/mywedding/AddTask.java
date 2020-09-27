package com.example.mywedding;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Date;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
import java.util.Objects;

import android.content.Context;
import android.view.MenuInflater;
import android.content.Intent;
import androidx.annotation.NonNull;

import com.example.mywedding.Database.DBHelper;
//import com.example.mywedding.Model.TaskModel;

public class AddTask extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    //initializing tasks values
    ImageButton btnDatePicker, btnSubAdd, newTaskSaveButton;
    RadioGroup radioGrp;
    RadioButton radioBtn1;
    EditText txtnewTask, txtNote, txtDate;
    Spinner spinner;
    private int mYear, mMonth, mDay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.header_add_task_name);

        txtnewTask = findViewById(R.id.task_name);
        txtNote = findViewById(R.id.editNote);

        //RadioGroup
        radioGrp = (RadioGroup) findViewById(R.id.radioGroup2);

        //Radio buttons
       // completeBtn1 = findViewById(R.id.taskComplete);
       // pendingBtn2 = findViewById(R.id.taskPending);


        // Spinner element
        spinner = (Spinner) findViewById(R.id.editTaskCategory);

        // Spinner click listener
        spinner.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("");
        categories.add("Clothing and Accessories");
        categories.add("Decorations");
        categories.add("Health and Beauty");
        categories.add("Food and Beverages");
        categories.add("Transport");
        categories.add("Music and Entertainment");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);

        //date picker
        btnDatePicker=(ImageButton)findViewById(R.id.imageButton2);
        txtDate=(EditText)findViewById(R.id.editDate);
        txtDate.setInputType(InputType.TYPE_NULL);
        btnDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Get Current Date
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                //date picker dialog box
                DatePickerDialog datePickerDialog = new DatePickerDialog(AddTask.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                txtDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });



    }

    //Save button in menubar
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

            // get selected radio button from radioGroup
            int selectedId = radioGrp.getCheckedRadioButtonId();
            // find the radiobutton by returned id
            radioBtn1 = (RadioButton) findViewById(selectedId);


            String spinnerValue = spinner.getSelectedItem().toString().trim();

            //checking whether task name and date is empty
            if(TextUtils.isEmpty(txtnewTask.getText())){
                Toast.makeText(this, "Please Enter Task Name", Toast.LENGTH_SHORT).show();
                txtnewTask.setError("Task Name is required!");
            } else if(TextUtils.isEmpty(txtDate.getText())){
                Toast.makeText(this, "Please Enter Task Date", Toast.LENGTH_SHORT).show();
                txtDate.setError("Task Date is required!");
            } else if(TextUtils.isEmpty(spinnerValue)) {
                Toast.makeText(this, "Please Select Task Category", Toast.LENGTH_SHORT).show();

            } else {
                //get string values of tasks data from table
                DBHelper myDB = new DBHelper(AddTask.this);
                myDB.insertNewTask(txtnewTask.getText().toString().trim(),
                        spinner.getSelectedItem().toString().trim(),
                        txtNote.getText().toString().trim(),
                        txtDate.getText().toString().trim(),
                        radioBtn1.getText().toString().trim());
                Intent intent = new Intent(AddTask.this, TaskList.class);
                startActivity(intent);

            }

        }
        if(id == android.R.id.home){
            Intent intent = new Intent(AddTask.this, TaskList.class);
            startActivity(intent);
        }
        return true;
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