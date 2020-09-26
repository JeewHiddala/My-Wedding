package com.example.mywedding;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
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

public class ViewTask extends AppCompatActivity {

    EditText tName_input, tNote_input, tDate_input, tCate_input, tStatus_input;
    TextView tID;
    String taskID, taskName, taskCategory, taskNote, taskDate, taskStatus;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_task);

        //
        tID = findViewById(R.id.textViewID);
        tName_input = findViewById(R.id.task_name2);
        tCate_input = findViewById(R.id.task_cate2);
        tNote_input = findViewById(R.id.sp_note2);
        tDate_input = findViewById(R.id.taskDate2);
        tStatus_input = findViewById(R.id.txtTaskStatus2);

        getAndSetIntentData();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.header_viewtask_name);


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }


    void getAndSetIntentData() {
        if(getIntent().hasExtra("taskID") && getIntent().hasExtra("taskName") && getIntent().hasExtra("taskCategory") &&
                getIntent().hasExtra("taskNote") && getIntent().hasExtra("taskDate") && getIntent().hasExtra("taskStatus")) {
            //Getting data from Intent
            taskID = getIntent().getStringExtra("taskID");
            taskName = getIntent().getStringExtra("taskName");
            taskCategory = getIntent().getStringExtra("taskCategory");
            taskNote = getIntent().getStringExtra("taskNote");
            taskDate = getIntent().getStringExtra("taskDate");
            taskStatus = getIntent().getStringExtra("taskStatus");

            //Setting Intent data
            tID.setText(taskID);
            tName_input.setText(taskName);
            tCate_input.setText(taskCategory);
            tNote_input.setText(taskNote);
            tDate_input.setText(taskDate);
            tStatus_input.setText(taskStatus);

        } else {
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.edit){
            //getting tasks values from tasks table
            taskID = tID.getText().toString();
            taskName = tName_input.getText().toString();
            taskCategory = tCate_input.getText().toString();
            taskNote = tNote_input.getText().toString();
            taskDate = tDate_input.getText().toString();
            taskStatus = tStatus_input.getText().toString();

            Intent intent = new Intent(ViewTask.this, EditTask.class);
            intent.putExtra("TASKID", taskID);
            intent.putExtra("TASKNAME", taskName);
            intent.putExtra("TASKCATEGORY", taskCategory);
            intent.putExtra("TASKNOTE", taskNote);
            intent.putExtra("TASKDATE", taskDate);
            intent.putExtra("TASKSTATUS", taskStatus);
            startActivity(intent);

        }

        if(id == android.R.id.home){

            Intent intent = new Intent(ViewTask.this, TaskList.class);
            startActivity(intent);
        }
        return true;
    }
}