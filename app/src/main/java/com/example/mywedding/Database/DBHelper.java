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
import android.text.Editable;
import com.example.mywedding.Vender;
import com.example.mywedding.VenderList;
import com.example.mywedding.VendorModel;
import com.example.mywedding.Models.BudgetModel;
import com.example.mywedding.Models.UserModel;
import com.example.mywedding.Guest;



//import static com.example.mywedding.Database.WeddingMaster.Guests.TABLE_NAME;

public class DBHelper extends SQLiteOpenHelper {
    private Context context;
    private static final int VERSION = 1;
    public static final String DATABASE_NAME = "MyWed.db";
    private static final String TASK_TABLE_NAME = "tasks";
    private SQLiteDatabase db;

    //tasks table column names
    private static final String COLUMN_NAME_TASKID = "task_id";
    private static final String COLUMN_NAME_TASKNAME = "task_name";
    private static final String COLUMN_NAME_TASKCATEGORY = "task_category";
    private static final String COLUMN_NAME_NOTE = "task_note";
    private static final String COLUMN_NAME_TASKDATE = "task_date";
    private static final String COLUMN_NAME_TASKSTATUS = "task_status";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Creating budget table
        String SQL_CREATE_ENTRIES1 =
                "CREATE TABLE " + BudgetMaster.Budget.TABLE_NAME + "(" +
                        BudgetMaster.Budget._ID + " INTEGER PRIMARY KEY," +
                        BudgetMaster.Budget.COLUMN_NAME_BUDGETNAME + " TEXT," +
                        BudgetMaster.Budget.COLUMN_NAME_AMOUNT + " TEXT," +
                        BudgetMaster.Budget.COLUMN_NAME_NOTES + " TEXT," +
                        BudgetMaster.Budget.COLUMN_NAME_CATEGORY + " TEXT," +
                        BudgetMaster.Budget.COLUMN_NAME_PAID + " TEXT," +
                        BudgetMaster.Budget.COLUMN_NAME_DATE + " TEXT," +
                        BudgetMaster.Budget.COLUMN_NAME_STATUS + " TEXT)";
        db.execSQL(SQL_CREATE_ENTRIES1);

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

        String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + VendorMaster.Vendors.TABLE_NAME +  " (" +
                        VendorMaster.Vendors._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        VendorMaster.Vendors.COLUMN_NAME_VNAME + " TEXT," +
                        VendorMaster.Vendors.COLUMN_NAME_CATEGORY + " TEXT," +
                        VendorMaster.Vendors.COLUMN_NAME_CONTACTNO + " TEXT," +
                        VendorMaster.Vendors.COLUMN_NAME_DESCRIPTION + " TEXT," +
                        VendorMaster.Vendors.COLUMN_NAME_STATUS + " INTEGER," +
                        VendorMaster.Vendors.COLUMN_NAME_AMOUNT + " TEXT)";
        db.execSQL(SQL_CREATE_ENTRIES);   //run query and  create table

        //Creating tasks table
        String SQL_query =
                "CREATE TABLE " + TASK_TABLE_NAME + " (" +
                COLUMN_NAME_TASKID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_NAME_TASKNAME + " TEXT," +
                COLUMN_NAME_TASKCATEGORY + " TEXT," +
                COLUMN_NAME_NOTE + " TEXT," +
                COLUMN_NAME_TASKDATE + " TEXT," +
                COLUMN_NAME_TASKSTATUS + " TEXT)";

        db.execSQL(SQL_query);

        //Guest Table
        String SQL_CREATE_ENTRIES3 =
                "CREATE TABLE " + GuestMaster.Guests.GTABLE_NAME + " (" +
                        GuestMaster.Guests._ID + " INTEGER PRIMARY KEY," +
                        GuestMaster.Guests.COLUMN_NAME_GNAME + " TEXT," +
                        GuestMaster.Guests.COLUMN_NAME_GENDER + " TEXT," +
                        GuestMaster.Guests.COLUMN_NAME_NOTES + " TEXT," +
                        GuestMaster.Guests.COLUMN_NAME_STATUSS + " INTEGER," +
                        GuestMaster.Guests.COLUMN_NAME_PHONE + " TEXT," +
                        GuestMaster.Guests.COLUMN_NAME_ADDRESS + " TEXT," +
                        GuestMaster.Guests.COLUMN_NAME_EMAIL + " TEXT)";
        db.execSQL(SQL_CREATE_ENTRIES3);
    }

    @Override
    public void onUpgrade(@Nullable SQLiteDatabase db, int oldVersion, int newVersion) { //helps to create a another new table
        String DROP_TABLE_QUERY = "DROP TABLE IF EXISTS "+ VendorMaster.Vendors.TABLE_NAME;
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
        values.put(VendorMaster.Vendors.COLUMN_NAME_VNAME,vendorName);
        values.put(VendorMaster.Vendors.COLUMN_NAME_CATEGORY,category);
        values.put(VendorMaster.Vendors.COLUMN_NAME_CONTACTNO,contactNo);
        values.put(VendorMaster.Vendors.COLUMN_NAME_DESCRIPTION,description);
        values.put(VendorMaster.Vendors.COLUMN_NAME_STATUS,status);
        values.put(VendorMaster.Vendors.COLUMN_NAME_AMOUNT,amount);

        //saving the new row, returning the primary key value of the new row
        long newRowId = db.insert(VendorMaster.Vendors.TABLE_NAME,null,values);
        return newRowId;
    }

    //count vendor table records
    public int countVendor(){
        SQLiteDatabase db = getReadableDatabase();
        String query  = "SELECT * FROM "+ VendorMaster.Vendors.TABLE_NAME;

        //selection args use to declare where conditions
        //cursor get count gives the numbers of the rows in the database as integer value
        Cursor cursor = db.rawQuery(query,null);
        return cursor.getCount();
    }

    //get all vendors into a list
    public List<VendorModel> getAllVendors(){

        List<VendorModel> Vendors = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM "+VendorMaster.Vendors.TABLE_NAME;

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
        db.delete(VendorMaster.Vendors.TABLE_NAME,"_id =?",new String[]{String.valueOf(id)});      //set delete query by getting id and convert id to integer
        //db.close();  //close the database connection
    }

    //get a single vendor
    public VendorModel getSingleVendor(int id) {
        SQLiteDatabase db = getWritableDatabase();          //create new object for SQLite database object
        //write database query and assign it to the cursor object
        Cursor cursor = db.query(VendorMaster.Vendors.TABLE_NAME, new String[]{VendorMaster.Vendors._ID, VendorMaster.Vendors.COLUMN_NAME_VNAME, VendorMaster.Vendors.COLUMN_NAME_CATEGORY, VendorMaster.Vendors.COLUMN_NAME_CONTACTNO, VendorMaster.Vendors.COLUMN_NAME_DESCRIPTION, VendorMaster.Vendors.COLUMN_NAME_STATUS, VendorMaster.Vendors.COLUMN_NAME_AMOUNT},
                VendorMaster.Vendors._ID + "= ?", new String[]{String.valueOf(id)}, null, null, null);

        VendorModel vendorModel;            //create model class (VendorModel) object

        if (cursor != null) {                 //check values available
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

        //Only one user is in the app. Therefore the checking user id always 1
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

        //query to select all rows from the tables
        String query = "SELECT * FROM " + BudgetMaster.Budget.TABLE_NAME;

        Cursor cursor = db.rawQuery(query,null);
        return cursor.getCount();
    }

    //count paid budgets
    public int countPaidBudgets(){
        SQLiteDatabase db = getReadableDatabase();

        //query to select all rows from the tables where status is paid
        String query = "SELECT * FROM " + BudgetMaster.Budget.TABLE_NAME + " WHERE " + BudgetMaster.Budget.COLUMN_NAME_STATUS + " = 'Paid'";

        Cursor cursor = db.rawQuery(query,null );
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
        db.close(); //closing the database connection
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


    // get a single vendor for view details
    public VendorModel getSingleViewVendor(int id){

        SQLiteDatabase db = getWritableDatabase();          //create new object for SQLite database object
        //write database query and assign it to the cursor object
        Cursor cursor = db.query(VendorMaster.Vendors.TABLE_NAME,new String[]{VendorMaster.Vendors._ID,VendorMaster.Vendors.COLUMN_NAME_VNAME,VendorMaster.Vendors.COLUMN_NAME_CATEGORY,VendorMaster.Vendors.COLUMN_NAME_CONTACTNO,VendorMaster.Vendors.COLUMN_NAME_DESCRIPTION,VendorMaster.Vendors.COLUMN_NAME_STATUS,VendorMaster.Vendors.COLUMN_NAME_AMOUNT},
                VendorMaster.Vendors._ID + "= ?",new String[]{String.valueOf(id)},null,null,null);

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
        values.put(VendorMaster.Vendors.COLUMN_NAME_VNAME,vendorModel.getVendorname());
        values.put(VendorMaster.Vendors.COLUMN_NAME_CATEGORY,vendorModel.getCategory());
        values.put(VendorMaster.Vendors.COLUMN_NAME_CONTACTNO,vendorModel.getContactno());
        values.put(VendorMaster.Vendors.COLUMN_NAME_DESCRIPTION,vendorModel.getDescription());
        values.put(VendorMaster.Vendors.COLUMN_NAME_STATUS,vendorModel.getStatus());
        values.put(VendorMaster.Vendors.COLUMN_NAME_AMOUNT,vendorModel.getAmount());

        //check affected no of rows.if status = 0,there is not affected rows.if status = 1, there is a affected row
        int status = db.update(VendorMaster.Vendors.TABLE_NAME,values,VendorMaster.Vendors._ID +" =?",
                new String[]{String.valueOf(vendorModel.getId())});

        db.close();    //close database
        return status;
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
        long result = db.insert(TASK_TABLE_NAME, null, values);
        if(result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Added Successfully!", Toast.LENGTH_SHORT).show();
        }

    }

    //count tasks table records
    public int countTasks(){
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM " + TASK_TABLE_NAME;
        Cursor cursor = db.rawQuery(query,null);
        return cursor.getCount();
    }

    //count completed tasks
    public int countCompletedTasks(){
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM " + TASK_TABLE_NAME + " WHERE " + COLUMN_NAME_TASKSTATUS + " = 'Completed'";
        Cursor cursor = db.rawQuery(query,null );
        return cursor.getCount();
    }

    //read all task values
    public Cursor readAllData() {
        String query = "SELECT * FROM " + TASK_TABLE_NAME;
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

        long result = db.update(TASK_TABLE_NAME, values, "task_id=?", new String[]{row_id});
        if(result == -1) {
            Toast.makeText(context, "Failed to Update", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Successfully Updated!", Toast.LENGTH_SHORT).show();
        }
    }

    //delete one task row
    public void deleteOneRow(String row_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TASK_TABLE_NAME, "task_id=?", new String[]{row_id});
        if(result == -1) {
            Toast.makeText(context, "Failed to Delete", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Successfully Deleted!", Toast.LENGTH_SHORT).show();
        }
    }

    //delete all task details row
    public void deleteAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TASK_TABLE_NAME);
    }


    //adding a guest to the table
    public long addGuest(String guestName, String gender, String notes, String status, String phone, String address, String eMail) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(GuestMaster.Guests.COLUMN_NAME_GNAME,guestName);
        values.put(GuestMaster.Guests.COLUMN_NAME_GENDER,gender);
        values.put(GuestMaster.Guests.COLUMN_NAME_NOTES,notes);
        values.put(GuestMaster.Guests.COLUMN_NAME_STATUSS,status);
        values.put(GuestMaster.Guests.COLUMN_NAME_PHONE,phone);
        values.put(GuestMaster.Guests.COLUMN_NAME_ADDRESS,address);
        values.put(GuestMaster.Guests.COLUMN_NAME_EMAIL,eMail);

        long newRowId = db.insert(GuestMaster.Guests.GTABLE_NAME,null,values);
        return newRowId;

    }
    //the list view of guests' names
    public List<Guest> getAllGuests(){

        List<Guest> guests = new ArrayList();
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM "+ GuestMaster.Guests.GTABLE_NAME;

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
        db.delete(GuestMaster.Guests.GTABLE_NAME, GuestMaster.Guests._ID +" =?", new String[]{String.valueOf(id)});
        db.close();
    }

    //retrieve a single guest entry in the view page
    public Guest getSingleGuest(int id) {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.query(GuestMaster.Guests.GTABLE_NAME, new String[]{GuestMaster.Guests._ID, GuestMaster.Guests.COLUMN_NAME_GNAME, GuestMaster.Guests.COLUMN_NAME_GENDER, GuestMaster.Guests.COLUMN_NAME_NOTES,
                GuestMaster.Guests.COLUMN_NAME_STATUSS, GuestMaster.Guests.COLUMN_NAME_PHONE, GuestMaster.Guests.COLUMN_NAME_ADDRESS, GuestMaster.Guests.COLUMN_NAME_EMAIL}, GuestMaster.Guests._ID  +"= ?", new String[]{String.valueOf(id)}, null, null, null);

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

        values.put(GuestMaster.Guests.COLUMN_NAME_GNAME,guest.getGuestName());
        values.put(GuestMaster.Guests.COLUMN_NAME_GENDER,guest.getGender());
        values.put(GuestMaster.Guests.COLUMN_NAME_NOTES,guest.getNotes());
        values.put(GuestMaster.Guests.COLUMN_NAME_STATUSS,guest.getStatus());
        values.put(GuestMaster.Guests.COLUMN_NAME_PHONE,guest.getPhone());
        values.put(GuestMaster.Guests.COLUMN_NAME_ADDRESS,guest.getAddress());
        values.put(GuestMaster.Guests.COLUMN_NAME_EMAIL,guest.geteMail());

        int stat = db.update(GuestMaster.Guests.GTABLE_NAME,values, GuestMaster.Guests._ID +" =?", new String[]{String.valueOf(guest.getId())});

        db.close();
        return stat;
    }

}
