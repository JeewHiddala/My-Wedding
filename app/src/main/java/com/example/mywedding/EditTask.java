package com.example.mywedding;


import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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


import com.example.mywedding.Database.DBHelper;

public class EditTask extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    ImageButton btnDatePicker2, btnDelete;
    TextView txtID;
    RadioGroup taskEditRg1;
    RadioButton editTaskradioBtn1, editTaskradioBtn2, editTaskradioBtn3;
    //Spinner spinner5;
    EditText txtName_input, txtNote_input, txtCate_input, txtDate;
    String taskID, taskName, taskNote, taskDate, taskCategory, taskStatus;
    private int mYear, mMonth, mDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task);
        //referencing tasks values
        txtName_input = findViewById(R.id.task_name);
        txtNote_input = findViewById(R.id.sp_note_edit);
        txtCate_input = findViewById(R.id.task_category);
        txtDate = (EditText)findViewById(R.id.editDate);
        txtID = findViewById(R.id.txtEditViewID);

        //Getting Intent data
        Intent intent = getIntent();
        taskID = intent.getStringExtra("TASKID");
        taskName = intent.getStringExtra("TASKNAME");
        taskCategory = intent.getStringExtra("TASKCATEGORY");
        taskNote = intent.getStringExtra("TASKNOTE");
        taskDate = intent.getStringExtra("TASKDATE");
        taskStatus = intent.getStringExtra("TASKSTATUS");

        //Radio Group
        taskEditRg1 = findViewById(R.id.radioGroup2);

        //Radio Buttons
        editTaskradioBtn1 = findViewById(R.id.taskComplete2);
        editTaskradioBtn2 = findViewById(R.id.taskPending2);

        //Radio Button Task Status
        if(taskStatus.equals("Pending")){
            editTaskradioBtn2.setChecked(true);
        } else {
            editTaskradioBtn1.setChecked(true);
        }

        //delete button
        btnDelete = findViewById(R.id.deleteButton2);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });

        //menu header name
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.header_edittask_name);

        //date picker
        btnDatePicker2=(ImageButton)findViewById(R.id.dateButton2);

        txtDate.setInputType(InputType.TYPE_NULL);
        btnDatePicker2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Get Current Date
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                //date picker dialog box
                DatePickerDialog datePickerDialog = new DatePickerDialog(EditTask.this,
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



        //Setting Intent data
        txtID.setText(taskID);
        txtName_input.setText(taskName);
        txtCate_input.setText(taskCategory);
        txtNote_input.setText(taskNote);
        txtDate.setText(taskDate);



    }

    //menu header save button initialize
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
            int selectedId = taskEditRg1.getCheckedRadioButtonId();
            // find the radiobutton by returned id
            editTaskradioBtn3 = (RadioButton) findViewById(selectedId);

            //checking whether task name and date is empty
            if(TextUtils.isEmpty(txtName_input.getText())){
                Toast.makeText(this, "Please Enter Task Name", Toast.LENGTH_SHORT).show();
                txtName_input.setError("Task Name is required!");
            } else if(TextUtils.isEmpty(txtDate.getText())){
                Toast.makeText(this, "Please Enter Task Date", Toast.LENGTH_SHORT).show();
                txtDate.setError("Task Date is required!");
            } else {

                //First we call this
                DBHelper myDB = new DBHelper(EditTask.this);
                taskID = txtID.getText().toString().trim();
                taskName = txtName_input.getText().toString().trim();
                taskCategory = txtCate_input.getText().toString().trim();
                taskNote = txtNote_input.getText().toString().trim();
                taskDate = txtDate.getText().toString().trim();
                taskStatus = editTaskradioBtn3.getText().toString().trim();


                //And only then we call this
                myDB.updateData(taskID, taskName, taskCategory, taskNote, taskDate, taskStatus);

                Intent intent = new Intent(EditTask.this, TaskList.class);
                startActivity(intent);
            }

        }

        //Back button intent
        if(id == android.R.id.home){

            Intent intent = new Intent(EditTask.this, TaskList.class);
            startActivity(intent);
        }
        return true;
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

    //Delete confirm dialog box
    public void confirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + taskName + " ?");
        builder.setMessage("Are you sure you want to delete " + taskName + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DBHelper myDB = new DBHelper(EditTask.this);
                myDB.deleteOneRow(taskID);

                Intent intent = new Intent(EditTask.this, TaskList.class);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(EditTask.this, TaskList.class);
                startActivity(intent);
            }
        });
        builder.create().show();
    }



}