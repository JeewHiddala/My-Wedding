package com.example.mywedding;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.content.Intent;

public class TaskList extends AppCompatActivity {

    ImageButton btn_addTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);

       getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btn_addTask = findViewById(R.id.btn_add_task);
    }

        @Override
        protected void onResume() {
            super.onResume();
            btn_addTask.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(TaskList.this,AddTask.class);
                    startActivity(intent);
                }
            });
        }
}