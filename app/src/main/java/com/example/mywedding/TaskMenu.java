package com.example.mywedding;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.content.Intent;


public class TaskMenu extends AppCompatActivity {

    ImageButton btn_taskList2, btn_taskList3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_menu);

       getSupportActionBar().setDisplayHomeAsUpEnabled(true);
       getSupportActionBar().setTitle(R.string.header_taskmenu_name);

        btn_taskList2 = findViewById(R.id.taskListBtn1);
        btn_taskList3 = findViewById(R.id.CtasklistBtn);
    }

    @Override
    protected void onResume() {
        super.onResume();
        btn_taskList2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TaskMenu.this,TaskList.class);
                startActivity(intent);
            }
        });

        btn_taskList3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TaskMenu.this,CompleteTask.class);
                startActivity(intent);
            }
        });
    }


}