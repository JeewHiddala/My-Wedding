package com.example.mywedding.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.mywedding.Guest;

import java.util.ArrayList;
import java.util.List;

import static android.provider.BaseColumns._ID;
import static com.example.mywedding.Database.WeddingMaster.Guests.COLUMN_NAME_ADDRESS;
import static com.example.mywedding.Database.WeddingMaster.Guests.COLUMN_NAME_EMAIL;
import static com.example.mywedding.Database.WeddingMaster.Guests.COLUMN_NAME_GENDER;
import static com.example.mywedding.Database.WeddingMaster.Guests.COLUMN_NAME_GNAME;
import static com.example.mywedding.Database.WeddingMaster.Guests.COLUMN_NAME_NOTES;
import static com.example.mywedding.Database.WeddingMaster.Guests.COLUMN_NAME_PHONE;
import static com.example.mywedding.Database.WeddingMaster.Guests.COLUMN_NAME_STATUS;
import static com.example.mywedding.Database.WeddingMaster.Guests.GTABLE_NAME;

//import static com.example.mywedding.Database.WeddingMaster.Guests.TABLE_NAME;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MyWed.db";
    private SQLiteDatabase db;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + GTABLE_NAME + " (" +
                        WeddingMaster.Guests._ID + " INTEGER PRIMARY KEY," +
                        WeddingMaster.Guests.COLUMN_NAME_GNAME + " TEXT," +
                        WeddingMaster.Guests.COLUMN_NAME_GENDER + " TEXT," +
                        WeddingMaster.Guests.COLUMN_NAME_NOTES + " TEXT," +
                        WeddingMaster.Guests.COLUMN_NAME_STATUS + " INTEGER," +
                        WeddingMaster.Guests.COLUMN_NAME_PHONE + " TEXT," +
                        WeddingMaster.Guests.COLUMN_NAME_ADDRESS + " TEXT," +
                        WeddingMaster.Guests.COLUMN_NAME_EMAIL + " TEXT)";
        db.execSQL(SQL_CREATE_ENTRIES);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    //adding a guest to the table
    public long addGuest(String guestName, String gender, String notes, String status, String phone, String address, String eMail) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(WeddingMaster.Guests.COLUMN_NAME_GNAME,guestName);
        values.put(WeddingMaster.Guests.COLUMN_NAME_GENDER,gender);
        values.put(WeddingMaster.Guests.COLUMN_NAME_NOTES,notes);
        values.put(WeddingMaster.Guests.COLUMN_NAME_STATUS,status);
        values.put(WeddingMaster.Guests.COLUMN_NAME_PHONE,phone);
        values.put(WeddingMaster.Guests.COLUMN_NAME_ADDRESS,address);
        values.put(WeddingMaster.Guests.COLUMN_NAME_EMAIL,eMail);

        long newRowId = db.insert(GTABLE_NAME,null,values);
        return newRowId;

    }
    //the list view of guests' names
    public List<Guest> getAllGuests(){

        List<Guest> guests = new ArrayList();
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM "+ WeddingMaster.Guests.GTABLE_NAME;

        Cursor cursor = db.rawQuery(query,null);

        if(cursor.moveToFirst()){

            do{
                //create new Guest object
                Guest guest = new Guest();

                guest.setId((cursor.getInt(0)));
                guest.setGuestName((cursor.getString(1)));
                guest.setGender((cursor.getString(2)));
                guest.setNotes((cursor.getString(3)));
                guest.setStatus((cursor.getString(4)));
                guest.setPhone((cursor.getString(5)));
                guest.setAddress((cursor.getString(6)));
                guest.seteMail((cursor.getString(7)));

                guests.add(guest);

            }while (cursor.moveToNext());

        }
        return guests;

    }

    //delete guests
    public void deleteGuest(int id){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(GTABLE_NAME, WeddingMaster.Guests._ID +" =?", new String[]{String.valueOf(id)});
        db.close();
    }

    //retrieve a single guest entry in the view page
    public Guest getSingleGuest(int id) {
        SQLiteDatabase db = getWritableDatabase();
       Cursor cursor = db.query(GTABLE_NAME, new String[]{WeddingMaster.Guests._ID, WeddingMaster.Guests.COLUMN_NAME_GNAME, WeddingMaster.Guests.COLUMN_NAME_GENDER, WeddingMaster.Guests.COLUMN_NAME_NOTES,
                WeddingMaster.Guests.COLUMN_NAME_STATUS, WeddingMaster.Guests.COLUMN_NAME_PHONE, WeddingMaster.Guests.COLUMN_NAME_ADDRESS, WeddingMaster.Guests.COLUMN_NAME_EMAIL}, WeddingMaster.Guests._ID  +"= ?", new String[]{String.valueOf(id)}, null, null, null);

       Guest guest;
       if(cursor != null){
          cursor.moveToFirst();
           guest = new Guest(
                   cursor.getInt(0),
                   cursor.getString(1),
                   cursor.getString(2),
                   cursor.getString(3),
                   cursor.getString(4),
                   cursor.getString(5),
                   cursor.getString(6),
                   cursor.getString(7)
           );

          return guest;
       }
       return null;
    }
    //update a single guest entry
    public int singleGuest(Guest guest){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(WeddingMaster.Guests.COLUMN_NAME_GNAME,guest.getGuestName());
        values.put(WeddingMaster.Guests.COLUMN_NAME_GENDER,guest.getGender());
        values.put(WeddingMaster.Guests.COLUMN_NAME_NOTES,guest.getNotes());
        values.put(WeddingMaster.Guests.COLUMN_NAME_STATUS,guest.getStatus());
        values.put(WeddingMaster.Guests.COLUMN_NAME_PHONE,guest.getPhone());
        values.put(WeddingMaster.Guests.COLUMN_NAME_ADDRESS,guest.getAddress());
        values.put(WeddingMaster.Guests.COLUMN_NAME_EMAIL,guest.geteMail());

        int stat = db.update(GTABLE_NAME,values, _ID +" =?", new String[]{String.valueOf(guest.getId())});

        db.close();
        return stat;
    }
}
