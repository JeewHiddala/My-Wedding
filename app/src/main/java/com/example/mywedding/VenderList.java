package com.example.mywedding;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mywedding.Database.DBHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class VenderList extends AppCompatActivity {

    private ListView listView;
    private TextView count;
    private FloatingActionButton fabadd;

    private DBHelper dbHelper;
    Context context;

    //used show data in a list view
    private List<VendorModel> Vendors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vender_list);

        //Back Button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Floating Action Button
        fabadd = (FloatingActionButton) findViewById(R.id.fabtnaddjh);

        //vendor list view
        listView  = (ListView) findViewById(R.id.vendorlist);

        //count
        count = (TextView) findViewById(R.id.vendorcount);

        //App bar name
        getSupportActionBar().setTitle(R.string.appbar_name_vendor_list);

        //context
        context = this;

        //DB helper
        dbHelper = new DBHelper(context);

        //get vendor counts from the table
        int countVendor = dbHelper.countVendor();
        count.setText("You have " +countVendor+" vendors");

        //Initialized the Array list of vendor model class
        Vendors = new ArrayList<>();
        //call the List<VendorModel> getAllVendors() method from db helper class
        Vendors = dbHelper.getAllVendors();

        //get values to the Vendor list
        VendorAdapter adapter = new VendorAdapter(context,R.layout.single_vender,Vendors);
        //set single vendor to the vendor list by using vendor adapter
        listView.setAdapter(adapter);

        //alert box
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                //make a model class object by get user clicked position
                final VendorModel vendor = Vendors.get(i);

                //create alert dialog box using alert dialog builder
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle(vendor.getVendorname());       //get the vendor name which user clicks using getter in VendorModel class
                builder.setMessage(vendor.getCategory());       //get the vendor category which user clicks using getter in VendorModel class

                //there are 3 categories.Positive buttons,Negative buttons and neutral buttons
                //create View button (redirect to the view vendor interface with data)
                builder.setPositiveButton("View", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(context,ViewVender.class);
                        intent.putExtra("id",String.valueOf(vendor.getId()));
                        startActivity(intent);
                        //startActivity(new Intent(context,ViewVender.class));       //direct to the ViewVendor interface
                    }
                });
                //create Delete button on alert dialog box
                builder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dbHelper.deleteVendor(vendor.getId());                  //call the deleteVendor method from dbHelper class
                        startActivity(new Intent(context,VenderList.class));    //redirect to the VendorList interface
                    }
                });
                //create edit button
                builder.setNeutralButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent =new Intent(context,EditVender.class);                //transfer value to EditVendor interface
                        intent.putExtra("id",String.valueOf(vendor.getId()));        //pass values
                        startActivity(intent);                                              //start activity (EditVendor) with values
                    }
                });

                //show the alert dialog box
                builder.show();

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        fabadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VenderList.this, AddVender.class);
                startActivity(intent);
            }
        });

        /*btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VenderList.this, ViewVender.class);
                startActivity(intent);
            }
        });*/
    }
}