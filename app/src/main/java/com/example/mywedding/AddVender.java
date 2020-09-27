package com.example.mywedding;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.mywedding.Database.DBHelper;

public class AddVender extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private EditText name,contactno,description,amount;
    private RadioButton radioButton;
    private RadioGroup radioGroup;
    private Button done;
    private String text;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vender);

        //initialized the edit text fields
        name = (EditText) findViewById(R.id.namejh);
        contactno = (EditText) findViewById(R.id.contactnojh);
        description = (EditText) findViewById(R.id.descriptionjh);
        amount = (EditText) findViewById(R.id.amountjh);

        //radioGroup
        radioGroup = (RadioGroup) findViewById(R.id.toggle);

        //done button
        done = (Button) findViewById(R.id.done);

        //Back Button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //spinner
        final Spinner categoryadd = (Spinner)findViewById(R.id.categoryjh);

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(AddVender.this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.names));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categoryadd.setAdapter(myAdapter);
        categoryadd.setOnItemSelectedListener(this);

        //App bar name
        getSupportActionBar().setTitle(R.string.appbar_name_add_vendor);

        //get user inputs
        /*done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String vendorName = name.getText().toString();
                String vendorContactNo = contactno.getText().toString();
                String vendorDescription = description.getText().toString();
                String vendorAmount = amount.getText().toString();
                String vendorText = text.;


               // VendorModel vendorModel = new VendorModel(vendorName,vendorContactNo,vendorDescription,vendorAmount);
            }
        });*/

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        text = adapterView.getItemAtPosition(i).toString();
        Toast.makeText(adapterView.getContext(),text,Toast.LENGTH_SHORT).show();//Toast
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu_save,menu);
        return true;
    }

    //Button  action on AppBar
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.done) {

            //getting selected radio button from the radio group
            int selectedId = radioGroup.getCheckedRadioButtonId();
            radioButton = findViewById(selectedId);

            //checking whether budget amount and name is empty
            if (TextUtils.isEmpty(name.getText())) {
                Toast.makeText(this, "Please enter vendor payment", Toast.LENGTH_SHORT).show();   //show a toast with request validate data inputs
                name.setError("Vendor Payment is required!");
            } else if (TextUtils.isEmpty(contactno.getText())) {
                Toast.makeText(this, "Please enter vendor name", Toast.LENGTH_SHORT).show();        //show a toast with request validate data inputs
                contactno.setError("Vendor Name is required!");
            } else if (TextUtils.isEmpty(amount.getText())) {
                Toast.makeText(this, "Please enter vendor contact Number", Toast.LENGTH_SHORT).show();      //show a toast with request validate data inputs
                amount.setError("Vendor Contact Number is required!");
            }else {

                //add vendor with db helper
                DBHelper dbHelper = new DBHelper(this);

                long val = dbHelper.addVendor(name.getText().toString(), contactno.getText().toString(), text, description.getText().toString(), radioButton.getText().toString(), amount.getText().toString());

                if (val > 0) {
                    Toast.makeText(this, "Successfully Added", Toast.LENGTH_SHORT).show();   //toast msg
                    Intent intent = new Intent(AddVender.this, VenderList.class);        //transfer data to the vendor list page
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();     //toast msg
                    Intent intent = new Intent(AddVender.this, AddVender.class);         //redirect to same page
                    startActivity(intent);
                }
            }


           /* Intent intentadd = new Intent(AddVender.this, VenderList.class);
            startActivity(intentadd);

            //Toast
            Context context = getApplicationContext();
            CharSequence message = "Details added Successfully";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, message, duration);
            toast.show();*/
        }

        if(id == android.R.id.home){
            Intent intent = new Intent(AddVender.this, VenderList.class);
            startActivity(intent);
        }

       return super.onOptionsItemSelected(item);
    }



}