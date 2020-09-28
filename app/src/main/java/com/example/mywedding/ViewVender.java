package com.example.mywedding;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.mywedding.Database.DBHelper;

public class ViewVender extends AppCompatActivity {

    private TextView viewVendorName,viewVendorCategory,viewVendorContactNo,viewVendorDescription,viewVendorStatus,viewVendorAmount;
    private Context context;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_vender);

        context = this;
        dbHelper = new DBHelper(context);       //pass the context to this constructor

        //initialized the textView fields
        viewVendorName = (TextView) findViewById(R.id.viewvendornamejh);
        viewVendorCategory = (TextView) findViewById(R.id.viewcategojh);
        viewVendorContactNo = (TextView) findViewById(R.id.viewcontanojh);
        viewVendorDescription = (TextView) findViewById(R.id.viewdescripjh);
        viewVendorStatus = (TextView) findViewById(R.id.viewpaymentsatjh);
        viewVendorAmount = (TextView) findViewById(R.id.viewamounjh);

        //Back Button in android
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final String id = getIntent().getStringExtra("id");
        VendorModel vendorModel = dbHelper.getSingleViewVendor(Integer.parseInt(id));         //convert string id to integer and assign it tp the getSingleVendor method in DBHelper

        //App bar name
        getSupportActionBar().setTitle(R.string.appbar_name_view_vendor);

        //get values from database and set them to the editVendor editText by using getters
        viewVendorName.setText(vendorModel.getVendorname());
        viewVendorCategory.setText(vendorModel.getCategory());
        viewVendorContactNo.setText(vendorModel.getContactno());
        viewVendorDescription.setText(vendorModel.getDescription());
        viewVendorStatus.setText(vendorModel.getStatus());
        viewVendorAmount.setText(vendorModel.getAmount());

        System.out.println(id);

    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }*/

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        /*if(id == R.id.edit) {
            Intent intent = new Intent(ViewVender.this, EditVender.class);
            startActivity(intent);
        }*/
        if(id == android.R.id.home) {
            Intent intent = new Intent(ViewVender.this, VenderList.class);
            startActivity(intent);
        }

        return true;
       // return super.onOptionsItemSelected(item);
    }
}
