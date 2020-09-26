package com.example.mywedding.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

//import com.example.mywedding.Model.TaskModel;
import com.example.mywedding.EditTask;
import com.example.mywedding.R;
import com.example.mywedding.TaskList;
import com.example.mywedding.AddTask;
import com.example.mywedding.ViewTask;

import java.util.ArrayList;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {
   // private List<TaskModel> taskList;
   // private TaskList activity;

    private Context context;
    private ArrayList task_id, task_name, task_cate, task_note, task_date, task_status;
    //intializing ArrayList for TaskAdapter
    public TaskAdapter(Context context, ArrayList task_id, ArrayList task_name, ArrayList task_cate, ArrayList task_note,
                ArrayList task_date, ArrayList task_status) {
        //build a constructor
        this.context = context;
        this.task_id = task_id;
        this.task_name = task_name;
        this.task_cate = task_cate;
        this.task_note = task_note;
        this.task_date = task_date;
        this.task_status = task_status;
    }

    //get tasks view in array
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //converting design to java
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row, parent, false);

        return new ViewHolder(view);
    }

    //set ViewHolder values
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.txtTask_id.setText(String.valueOf(task_id.get(position)));
        holder.txtTask_name.setText(String.valueOf(task_name.get(position)));
        holder.txtTask_cate.setText(String.valueOf(task_cate.get(position)));
        holder.txtTask_note.setText(String.valueOf(task_note.get(position)));
        holder.txtTask_date.setText(String.valueOf(task_date.get(position)));
        holder.txtTask_status.setText(String.valueOf(task_status.get(position)));

        //get taskStatus radio button status
        String taskStatus1 = String.valueOf(task_status.get(position));
        String paid = "Completed";

        if (taskStatus1.equals(paid)) {
            holder.taskImageView.setVisibility(View.VISIBLE);
        }


        //get intent values
        holder.mainRowLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ViewTask.class);
                intent.putExtra("taskID", String.valueOf(task_id.get(position)));
                intent.putExtra("taskName", String.valueOf(task_name.get(position)));
                intent.putExtra("taskCategory", String.valueOf(task_cate.get(position)));
                intent.putExtra("taskNote", String.valueOf(task_note.get(position)));
                intent.putExtra("taskDate", String.valueOf(task_date.get(position)));
                intent.putExtra("taskStatus", String.valueOf(task_status.get(position)));


                context.startActivity(intent);

            }
        });
    }

    public int getItemCount() {
        return task_name.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtTask_id, txtTask_name, txtTask_cate, txtTask_note, txtTask_date, txtTask_status;
        ImageView taskImageView;
        //final int position;
        CardView mainRowLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //refering xml file values
            txtTask_id = itemView.findViewById(R.id.txtTaskID);
            txtTask_name = itemView.findViewById(R.id.txtTaskName);
            txtTask_cate = itemView.findViewById(R.id.txtTaskCategory);
            txtTask_note = itemView.findViewById(R.id.txtTaskNote);
            txtTask_date = itemView.findViewById(R.id.txtTaskDate);
            txtTask_status = itemView.findViewById(R.id.txtTaskStatus);

            mainRowLayout = itemView.findViewById(R.id.mainRowLayout);

            taskImageView = itemView.findViewById(R.id.completeTaskImg);

            taskImageView.setVisibility(itemView.INVISIBLE); //Completed image



        }


    }
}
