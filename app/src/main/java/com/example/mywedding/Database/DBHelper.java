package com.example.mywedding.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.mywedding.Models.BudgetModel;
import com.example.mywedding.Models.UserModel;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "MyWed.db";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Creating budget table
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

        //Creating User table
        String SQL_CREATE_ENTRIES2 =
                "CREATE TABLE " + UserMaster.User.TABLE_NAME + "(" +
                        UserMaster.User._ID + " INTEGER PRIMARY KEY," +
                        UserMaster.User.COLUMN_NAME_USERNAME + " TEXT," +
                        UserMaster.User.COLUMN_NAME_USEREMAIL + " TEXT," +
                        UserMaster.User.COLUMN_NAME_USERCONTACT + " TEXT," +
                        UserMaster.User.COLUMN_NAME_USERSTATUS + " TEXT," +
                        UserMaster.User.COLUMN_NAME_PARTNERNAME + " TEXT," +
                        UserMaster.User.COLUMN_NAME_PARTNEREMAIL + " TEXT," +
                        UserMaster.User.COLUMN_NAME_PARTNERCONTACT + " TEXT," +
                        UserMaster.User.COLUMN_NAME_PARTNERSTATUS + " TEXT," +
                        UserMaster.User.COLUMN_NAME_WEDDINGNAME + " TEXT," +
                        UserMaster.User.COLUMN_NAME_WEDDINGDATE + " TEXT)";
        db.execSQL(SQL_CREATE_ENTRIES2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    //adding function of the user
    public long addUser(String uName, String uEmail, String uContact, String uStatus, String pName, String pEmail, String pContact, String pStatus, String wName, String wDate){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(UserMaster.User.COLUMN_NAME_USERNAME, uName);
        contentValues.put(UserMaster.User.COLUMN_NAME_USEREMAIL, uEmail);
        contentValues.put(UserMaster.User.COLUMN_NAME_USERCONTACT, uContact);
        contentValues.put(UserMaster.User.COLUMN_NAME_USERSTATUS, uStatus);
        contentValues.put(UserMaster.User.COLUMN_NAME_PARTNERNAME, pName);
        contentValues.put(UserMaster.User.COLUMN_NAME_PARTNEREMAIL, pEmail);
        contentValues.put(UserMaster.User.COLUMN_NAME_PARTNERCONTACT, pContact);
        contentValues.put(UserMaster.User.COLUMN_NAME_PARTNERSTATUS, pStatus);
        contentValues.put(UserMaster.User.COLUMN_NAME_WEDDINGNAME, wName);
        contentValues.put(UserMaster.User.COLUMN_NAME_WEDDINGDATE, wDate);

        long newRow = db.insert(UserMaster.User.TABLE_NAME, null,contentValues);
        return newRow;
    }

    public UserModel getUserDetails(int id){
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.query(UserMaster.User.TABLE_NAME, new String[]{
                UserMaster.User._ID,
                UserMaster.User.COLUMN_NAME_USERNAME,
                UserMaster.User.COLUMN_NAME_USEREMAIL,
                UserMaster.User.COLUMN_NAME_USERCONTACT,
                UserMaster.User.COLUMN_NAME_USERSTATUS,
                UserMaster.User.COLUMN_NAME_PARTNERNAME,
                UserMaster.User.COLUMN_NAME_PARTNEREMAIL,
                UserMaster.User.COLUMN_NAME_PARTNERCONTACT,
                UserMaster.User.COLUMN_NAME_PARTNERSTATUS,
                UserMaster.User.COLUMN_NAME_WEDDINGNAME,
                UserMaster.User.COLUMN_NAME_WEDDINGDATE},
                UserMaster.User._ID + " =1",
                null,
                null,
                null,
                null,
                null);

        UserModel user;

        //checking the availability of data
        if(cursor != null){
            cursor.moveToFirst();

            //creating user object
            user = new UserModel(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getString(6),
                    cursor.getString(7),
                    cursor.getString(8),
                    cursor.getString(9),
                    cursor.getString(10)

            );
            return user;
        }
        //closing the connection
        db.close();

        return null;
    }

    //function to update user details
    public long updateUser(UserModel user){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        //setting content values
        values.put(UserMaster.User.COLUMN_NAME_USERNAME, user.getUserName());
        values.put(UserMaster.User.COLUMN_NAME_USEREMAIL, user.getUserEmail());
        values.put(UserMaster.User.COLUMN_NAME_USERCONTACT, user.getUserContact());
        values.put(UserMaster.User.COLUMN_NAME_USERSTATUS, user.getUserStatus());
        values.put(UserMaster.User.COLUMN_NAME_PARTNERNAME, user.getPartnerName());
        values.put(UserMaster.User.COLUMN_NAME_PARTNEREMAIL, user.getPartnerEmail());
        values.put(UserMaster.User.COLUMN_NAME_PARTNERCONTACT, user.getPartnerContact());
        values.put(UserMaster.User.COLUMN_NAME_PARTNERSTATUS, user.getPartnerStatus());
        values.put(UserMaster.User.COLUMN_NAME_WEDDINGNAME, user.getWeddingName());
        values.put(UserMaster.User.COLUMN_NAME_WEDDINGDATE, user.getWeddingDate());

        //update query
        long status = db.update(UserMaster.User.TABLE_NAME, values, UserMaster.User._ID + " =?",
                new String[]{String.valueOf(user.getId())});

        //closing the connection
        db.close();

        return status;
    }

    //adding function of the budget
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

    //count budget table records
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

    //Deleting item from the budget table
    public void deleteBudget(int id){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(BudgetMaster.Budget.TABLE_NAME, BudgetMaster.Budget._ID + " =?", new String[]{String.valueOf(id)});
        db.close();
    }

    //selecting an item from the budget
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

        //closing the database connection
        db.close();

        return null;
    }

    //updating function of the budget table
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
