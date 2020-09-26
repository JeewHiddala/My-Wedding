package com.example.mywedding.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;
//import com.example.mywedding.Model.TaskModel;
import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    private Context context;
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "MyWed.db";
    private static final String TABLE_NAME = "tasks";

    //tasks table column names
    private static final String COLUMN_NAME_TASKID = "task_id";
    private static final String COLUMN_NAME_TASKNAME = "task_name";
    private static final String COLUMN_NAME_TASKCATEGORY = "task_category";
    private static final String COLUMN_NAME_NOTE = "task_note";
    private static final String COLUMN_NAME_TASKDATE = "task_date";
    private static final String COLUMN_NAME_TASKSTATUS = "task_status";
   


    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
        this.context = context;
    }

    //Creating tasks table
      @Override
     public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " (" +
                        COLUMN_NAME_TASKID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_NAME_TASKNAME + " TEXT, " +
                        COLUMN_NAME_TASKCATEGORY + " TEXT, " +
                        COLUMN_NAME_NOTE + " TEXT, " +
                        COLUMN_NAME_TASKDATE + " TEXT," +
                        COLUMN_NAME_TASKSTATUS + " TEXT);";

        db.execSQL(query);

      }

      @Override
      public void onUpgrade(SQLiteDatabase db, int i, int i1) {

          // Drop older table if existed
          db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
          // Create tables again
          onCreate(db);
      }

      //insert all task values
    public void insertNewTask(String task_name, String task_cate, String task_note, String task_date, String task_status) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_TASKNAME, task_name);
        values.put(COLUMN_NAME_TASKCATEGORY, task_cate);
        values.put(COLUMN_NAME_NOTE, task_note);
        values.put(COLUMN_NAME_TASKDATE, task_date);
        values.put(COLUMN_NAME_TASKSTATUS, task_status);
        long result = db.insert(TABLE_NAME, null, values);
        if(result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Added Successfully!", Toast.LENGTH_SHORT).show();
        }

    }

    //read all task values
    public Cursor readAllData() {
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    //update all task values
    public void updateData(String row_id, String task_name, String task_cate, String task_note, String task_date, String task_status) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_TASKID, row_id);
        values.put(COLUMN_NAME_TASKNAME, task_name);
        values.put(COLUMN_NAME_TASKCATEGORY, task_cate);
        values.put(COLUMN_NAME_NOTE, task_note);
        values.put(COLUMN_NAME_TASKDATE, task_date);
        values.put(COLUMN_NAME_TASKSTATUS, task_status);

        long result = db.update(TABLE_NAME, values, "task_id=?", new String[]{row_id});
        if(result == -1) {
            Toast.makeText(context, "Failed to Update", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Successfully Updated!", Toast.LENGTH_SHORT).show();
        }
    }

    //delete one task row
    public void deleteOneRow(String row_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "task_id=?", new String[]{row_id});
        if(result == -1) {
            Toast.makeText(context, "Failed to Delete", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Successfully Deleted!", Toast.LENGTH_SHORT).show();
        }
    }

    //delete all task details row
    public void deleteAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }




}

