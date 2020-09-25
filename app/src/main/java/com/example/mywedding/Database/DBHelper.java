package com.example.mywedding.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.Editable;

import com.example.mywedding.Vender;
import com.example.mywedding.VenderList;
import com.example.mywedding.VendorModel;

import java.util.ArrayList;
import java.util.List;

import static com.example.mywedding.Database.weddingMaster.Vendors.COLUMN_NAME_AMOUNT;
import static com.example.mywedding.Database.weddingMaster.Vendors.COLUMN_NAME_CATEGORY;
import static com.example.mywedding.Database.weddingMaster.Vendors.COLUMN_NAME_CONTACTNO;
import static com.example.mywedding.Database.weddingMaster.Vendors.COLUMN_NAME_DESCRIPTION;
import static com.example.mywedding.Database.weddingMaster.Vendors.COLUMN_NAME_STATUS;
import static com.example.mywedding.Database.weddingMaster.Vendors.COLUMN_NAME_VNAME;
import static com.example.mywedding.Database.weddingMaster.Vendors.TABLE_NAME;
import static com.example.mywedding.Database.weddingMaster.Vendors._ID;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "MyWed.db";
    private SQLiteDatabase db;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_ENTRIES =
                    "CREATE TABLE " + TABLE_NAME +  " (" +
                            weddingMaster.Vendors._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                            weddingMaster.Vendors.COLUMN_NAME_VNAME + " TEXT," +
                            weddingMaster.Vendors.COLUMN_NAME_CATEGORY + " TEXT," +
                            weddingMaster.Vendors.COLUMN_NAME_CONTACTNO + " TEXT," +
                            weddingMaster.Vendors.COLUMN_NAME_DESCRIPTION + " TEXT," +
                            weddingMaster.Vendors.COLUMN_NAME_STATUS + " INTEGER," +
                            weddingMaster.Vendors.COLUMN_NAME_AMOUNT + " TEXT)";
                     db.execSQL(SQL_CREATE_ENTRIES);   //run query and  create table


        /*CREATE TABLE vendor (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, category TEXT, contact_no INTEGER, description TEXT, status
        started TEXT,finished TEXT); */
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) { //helps to create a another new table
        String DROP_TABLE_QUERY = "DROP TABLE IF EXISTS "+ TABLE_NAME;
        // Drop older table if existed
        db.execSQL(DROP_TABLE_QUERY);
        //Create tables again
        onCreate(db);
    }

    public long addVendor(String vendorName, String contactNo, String category, String description, String status, String amount ){
        //get the data repository in the write mode
        SQLiteDatabase db = getWritableDatabase();

        //create a new map of values,where column names the keys
        ContentValues values = new ContentValues();
        values.put(weddingMaster.Vendors.COLUMN_NAME_VNAME,vendorName);
        values.put(weddingMaster.Vendors.COLUMN_NAME_CATEGORY,category);
        values.put(weddingMaster.Vendors.COLUMN_NAME_CONTACTNO,contactNo);
        values.put(weddingMaster.Vendors.COLUMN_NAME_DESCRIPTION,description);
        values.put(weddingMaster.Vendors.COLUMN_NAME_STATUS,status);
        values.put(weddingMaster.Vendors.COLUMN_NAME_AMOUNT,amount);

        //saving the new row, returning the primary key value of the new row
        long newRowId = db.insert(TABLE_NAME,null,values);
        return newRowId;
    }

    //count vendor table records
    public int countVendor(){
        SQLiteDatabase db = getReadableDatabase();
        String query  = "SELECT * FROM "+ TABLE_NAME;

        //selection args use to declare where conditions
        //cursor get count gives the numbers of the rows in the database as integer value
        Cursor cursor = db.rawQuery(query,null);
        return cursor.getCount();
    }

    //get all vendors into a list
    public List<VendorModel> getAllVendors(){

        List<VendorModel> Vendors = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM "+TABLE_NAME;

        Cursor cursor = db.rawQuery(query,null);

        if(cursor.moveToFirst()){
            do {
                //Create new Vendor List object
                VendorModel vendor = new VendorModel();

                //
                vendor.setId(cursor.getInt(0));
                vendor.setVendorname(cursor.getString(1));
                vendor.setCategory(cursor.getString(2));
                vendor.setContactno(cursor.getString(3));
                vendor.setDescription(cursor.getString(4));
                vendor.setStatus(cursor.getString(5));
                vendor.setAmount(cursor.getString(6));

                //Vendors = [obj.objs,]
                Vendors.add(vendor);
            }while (cursor.moveToNext());
        }
        return Vendors;
    }

    // Delete Vendors
    public void deleteVendor(int id){

        SQLiteDatabase db = getWritableDatabase();      //create new object for SQLite database object
        db.delete(TABLE_NAME,"_id =?",new String[]{String.valueOf(id)});      //set delete query by getting id and convert id to integer
        //db.close();  //close the database connection
    }

    //get a single vendor
    public VendorModel getSingleVendor(int id){

        SQLiteDatabase db = getWritableDatabase();          //create new object for SQLite database object
        //write database query and assign it to the cursor object
        Cursor cursor = db.query(TABLE_NAME,new String[]{_ID,COLUMN_NAME_VNAME,COLUMN_NAME_CATEGORY,COLUMN_NAME_CONTACTNO,COLUMN_NAME_DESCRIPTION,COLUMN_NAME_STATUS,COLUMN_NAME_AMOUNT},
                _ID + "= ?",new String[]{String.valueOf(id)},null,null,null);

        VendorModel vendorModel;            //create model class (VendorModel) object

        if(cursor != null ){                 //check values available
            cursor.moveToFirst();
            vendorModel = new VendorModel(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getString(6)
            );    //pass the cursor index values in database to the parameters in VendorModel class while create a model class object

            return vendorModel;  //return vendorModel object
        }
        return null;
    }

    // get a single vendor for view details
    public VendorModel getSingleViewVendor(int id){

        SQLiteDatabase db = getWritableDatabase();          //create new object for SQLite database object
        //write database query and assign it to the cursor object
        Cursor cursor = db.query(TABLE_NAME,new String[]{_ID,COLUMN_NAME_VNAME,COLUMN_NAME_CATEGORY,COLUMN_NAME_CONTACTNO,COLUMN_NAME_DESCRIPTION,COLUMN_NAME_STATUS,COLUMN_NAME_AMOUNT},
                _ID + "= ?",new String[]{String.valueOf(id)},null,null,null);

        VendorModel vendorModel;            //create model class (VendorModel) object

        if(cursor != null ){                 //check values available
            cursor.moveToFirst();
            vendorModel = new VendorModel(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getString(6)
            );    //pass the cursor index values in database to the parameters in VendorModel class while create a model class object

            return vendorModel;  //return vendorModel object
        }
        return null;
    }

    public int updateSingleVendor(VendorModel vendorModel){

        SQLiteDatabase db = getWritableDatabase();          //create new object for SQLite database object

        //create a new map of values,where column names the keys
        ContentValues values = new ContentValues();
        values.put(weddingMaster.Vendors.COLUMN_NAME_VNAME,vendorModel.getVendorname());
        values.put(weddingMaster.Vendors.COLUMN_NAME_CATEGORY,vendorModel.getCategory());
        values.put(weddingMaster.Vendors.COLUMN_NAME_CONTACTNO,vendorModel.getContactno());
        values.put(weddingMaster.Vendors.COLUMN_NAME_DESCRIPTION,vendorModel.getDescription());
        values.put(weddingMaster.Vendors.COLUMN_NAME_STATUS,vendorModel.getStatus());
        values.put(weddingMaster.Vendors.COLUMN_NAME_AMOUNT,vendorModel.getAmount());

        //check affected no of rows.if status = 0,there is not affected rows.if status = 1, there is a affected row
        int status = db.update(TABLE_NAME,values,_ID +" =?",
                new String[]{String.valueOf(vendorModel.getId())});

        db.close();    //close database
        return status;
    }

}
