package com.example.mywedding.Model;

public class TaskModel {
    private int id, status;
    private String task, task_category, task_note, date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getTask_category() {
        return task_category;
    }

    public void setTask_category(String task_category) {
        this.task_category = task_category;
    }

    public String getTask_note() {
        return task_note;
    }

    public void setTask_note(String task_note) {
        this.task_note = task_note;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
