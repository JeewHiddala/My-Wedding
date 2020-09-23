package com.example.mywedding.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.mywedding.Models.BudgetModel;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "MyWed.db";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + BudgetMaster.Budget.TABLE_NAME + "(" +
                        BudgetMaster.Budget._ID + " INTEGER PRIMARY KEY," +
                        BudgetMaster.Budget.COLUMN_NAME_BUDGETNAME + " TEXT," +
                        BudgetMaster.Budget.COLUMN_NAME_AMOUNT + " TEXT," +
                        BudgetMaster.Budget.COLUMN_NAME_NOTES + " TEXT," +
                        BudgetMaster.Budget.COLUMN_NAME_CATEGORY + " TEXT," +
                        BudgetMaster.Budget.COLUMN_NAME_PAID + " TEXT," +
                        BudgetMaster.Budget.COLUMN_NAME_DATE + " TEXT," +
                        BudgetMaster.Budget.COLUMN_NAME_STATUS + " TEXT)";
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    //adding function
    public long addBudget(String name, String amount, String notes, String category, String paid, String date, String status){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(BudgetMaster.Budget.COLUMN_NAME_BUDGETNAME,name);
        values.put(BudgetMaster.Budget.COLUMN_NAME_AMOUNT,amount);
        values.put(BudgetMaster.Budget.COLUMN_NAME_NOTES,notes);
        values.put(BudgetMaster.Budget.COLUMN_NAME_CATEGORY,category);
        values.put(BudgetMaster.Budget.COLUMN_NAME_PAID,paid);
        values.put(BudgetMaster.Budget.COLUMN_NAME_DATE,date);
        values.put(BudgetMaster.Budget.COLUMN_NAME_STATUS,status);

        long newRowId = db.insert(BudgetMaster.Budget.TABLE_NAME,null,values);
        return newRowId;
    }

    //count table records
    public int countBudgets(){
        SQLiteDatabase db = getReadableDatabase();

        String query = "SELECT * FROM " + BudgetMaster.Budget.TABLE_NAME;

        Cursor cursor = db.rawQuery(query,null);
        return cursor.getCount();
    }

    //Listing all the budgets
    public List<BudgetModel> getAllBudgets(){
        List<BudgetModel> budget = new ArrayList();
        SQLiteDatabase db = getReadableDatabase();

        String query = "SELECT * FROM " + BudgetMaster.Budget.TABLE_NAME;

        Cursor cursor = db.rawQuery(query,null);

        //move the cursor to the first row
        if(cursor.moveToFirst()){
            do{
                //creating new object
                BudgetModel b = new BudgetModel();

                b.setId(cursor.getInt(0));
                b.setBudgetName(cursor.getString(1));
                b.setAmount(cursor.getDouble(2));
                b.setNotes(cursor.getString(3));
                b.setCategory(cursor.getString(4));
                b.setPaidAmount(cursor.getDouble(5));
                b.setPaidDate(cursor.getString(6));
                b.setStatus(cursor.getString(7));

                //adding object to the list
                budget.add(b);
            }while(cursor.moveToNext());
        }

        return budget;
    }

    //Deleting item
    public void deleteBudget(int id){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(BudgetMaster.Budget.TABLE_NAME, BudgetMaster.Budget._ID + " =?", new String[]{String.valueOf(id)});
        db.close();
    }

    //selecting an item
    public BudgetModel getSingleBudget(int id){
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.query(BudgetMaster.Budget.TABLE_NAME, new String[]{
                BudgetMaster.Budget._ID,
                BudgetMaster.Budget.COLUMN_NAME_BUDGETNAME,
                BudgetMaster.Budget.COLUMN_NAME_AMOUNT,
                BudgetMaster.Budget.COLUMN_NAME_NOTES,
                BudgetMaster.Budget.COLUMN_NAME_CATEGORY,
                BudgetMaster.Budget.COLUMN_NAME_PAID,
                BudgetMaster.Budget.COLUMN_NAME_DATE,
                BudgetMaster.Budget.COLUMN_NAME_STATUS},
                BudgetMaster.Budget._ID + " =?",
                new String[]{String.valueOf(id)}, //value for the above ? mark
                null,
                null,
                null);

        BudgetModel budget;

        //checking the data is available
        if(cursor != null){
            cursor.moveToFirst();
            //creating an object
            budget = new BudgetModel(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getDouble(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getDouble(5),
                    cursor.getString(6),
                    cursor.getString(7)
            );
            return budget;
        }
        return null;
    }

    //updating function
    public long updateBudget(BudgetModel model){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        //setting content values
        contentValues.put(BudgetMaster.Budget.COLUMN_NAME_BUDGETNAME, model.getBudgetName());
        contentValues.put(BudgetMaster.Budget.COLUMN_NAME_AMOUNT, model.getAmount());
        contentValues.put(BudgetMaster.Budget.COLUMN_NAME_NOTES, model.getNotes());
        contentValues.put(BudgetMaster.Budget.COLUMN_NAME_CATEGORY, model.getCategory());
        contentValues.put(BudgetMaster.Budget.COLUMN_NAME_PAID, model.getPaidAmount());
        contentValues.put(BudgetMaster.Budget.COLUMN_NAME_DATE, model.getPaidDate());
        contentValues.put(BudgetMaster.Budget.COLUMN_NAME_STATUS, model.getStatus());

        //update query
        long status = db.update(BudgetMaster.Budget.TABLE_NAME, contentValues, BudgetMaster.Budget._ID + " =?",
                new String[] {String.valueOf(model.getId())});

        //closing the connection
        db.close();

        return status;
    }

}
