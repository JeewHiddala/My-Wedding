package com.example.mywedding;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mywedding.Adapter.TaskAdapter;
import com.example.mywedding.Database.DBHelper;
//import com.example.mywedding.Model.TaskModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class TaskList extends AppCompatActivity {

    Button btn_viewTask;
    RecyclerView tasksRecyclerView;
    FloatingActionButton btn_addTask;
    TaskAdapter tasksAdapter;
    ImageView emptyImage;
    TextView txtNoData;

    DBHelper myDB;
    ArrayList<String> task_id, task_name, task_cate, task_note, task_date, task_status;
    //private List<TaskModel> taskList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.header_tasklist_name);


        tasksRecyclerView = findViewById(R.id.tasksRecyclerView);
        btn_addTask = findViewById(R.id.btn_add_task);

        //empty data
        emptyImage = findViewById(R.id.empty_imageView);
        txtNoData = findViewById(R.id.textViewEmptyData);

        myDB = new DBHelper(TaskList.this);
        task_id = new ArrayList<>();
        task_name = new ArrayList<>();
        task_cate = new ArrayList<>();
        task_note = new ArrayList<>();
        task_date = new ArrayList<>();
        task_status = new ArrayList<>();

        storeDataInArrays();

        tasksAdapter = new TaskAdapter(TaskList.this, task_id, task_name, task_cate, task_note, task_date, task_status);
        tasksRecyclerView.setAdapter(tasksAdapter);
        tasksRecyclerView.setLayoutManager(new LinearLayoutManager(TaskList.this));

        //btn_viewTask = findViewById(R.id.button1);

    }

    void storeDataInArrays() {
        Cursor cursor = myDB.readAllData();
        if(cursor.getCount() == 0) {
            emptyImage.setVisibility(View.VISIBLE);
            txtNoData.setVisibility(View.VISIBLE);
            //Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        } else {
            while  (cursor.moveToNext()) {
                task_name.add(cursor.getString(1));
                task_cate.add(cursor.getString(2));
                task_note.add(cursor.getString(3));
                task_date.add(cursor.getString(4));
                task_status.add(cursor.getString(5));
                task_id.add(cursor.getString(0));

            }
            emptyImage.setVisibility(View.GONE);
            txtNoData.setVisibility(View.GONE);
        }
    }

   /* private void loadTaskList() {
        ArrayList<String> taskList = dbHelper.getTaskList();
        if(mAdapter==null) {
            mAdapter = ArrayAdapter<String>(this,R.layout.row,R.id.task_title,taskList);
            taskListView.setAdapter(mAdapter);
        }
        else {
            mAdapter.clear();
            mAdapter.addAll(taskList);
            mAdapter.notifyDataSetChanged();
        }
    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_delete,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.delete_all){
            Toast.makeText(this, "Delete All", Toast.LENGTH_SHORT).show();
            confirmDialog();

        }

        if(id == android.R.id.home){

            Intent intent = new Intent(TaskList.this, Home.class);
            startActivity(intent);
        }
        return true;
    }

    //confirm dialog box for delete all tasks list
    public void confirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete All Tasks ?");
        builder.setMessage("Are you sure you want to delete all tasks ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //First we call this
                DBHelper myDB = new DBHelper(TaskList.this);
                myDB.deleteAllData();
                //Refresh Activity
                Intent intent = new Intent(TaskList.this, TaskList.class);
                startActivity(intent);
                //finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }


    @Override
        protected void onResume() {
            super.onResume();
            //OnClickListener for task add button
            btn_addTask.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(TaskList.this, AddTask.class);
                    startActivity(intent);
                }
            });

        }
}