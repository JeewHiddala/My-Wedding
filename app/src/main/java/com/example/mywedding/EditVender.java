package com.example.mywedding;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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

import com.example.mywedding.Database.DBHelper;

public class EditVender extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private EditText edname,edcontactno,eddescription,edamount;
    private RadioButton radioButton,radioButton1,radioButton2;
    private RadioGroup radioGroup;
    private Button done;
    private DBHelper dbHelper;
    private Context context;
    private Long updatedDate;
    private Spinner editcategoryadd;
    private String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_vender);

        context = this;
        dbHelper = new DBHelper(context);       //pass the context to this constructor

        //initialized the edit text fields
        edname = (EditText) findViewById(R.id.editnamejh);
        edcontactno = (EditText) findViewById(R.id.editcontactnojh);
        eddescription = (EditText) findViewById(R.id.editdescriptionjh);
        edamount = (EditText) findViewById(R.id.editamountjh);

        //radioGroup
        radioGroup = (RadioGroup) findViewById(R.id.toggle);
        //radioButtons
        radioButton1 = (RadioButton) findViewById(R.id.pendingjh);
        radioButton2 = (RadioButton) findViewById(R.id.completedjh);

        //done button
        done = (Button) findViewById(R.id.done);

       //Back Button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final String id = getIntent().getStringExtra("id");
        VendorModel vendorModel = dbHelper.getSingleVendor(Integer.parseInt(id));         //convert string id to integer and assign it tp the getSingleVendor method in DBHelper

        //spinner
        editcategoryadd = (Spinner) findViewById(R.id.editcategoryjh);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(EditVender.this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.names));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        editcategoryadd.setAdapter(myAdapter);

        //final VendorModel vendor = new VendorModel();

        //fetching spinner values from SQLite Database
        String selectedCategory = vendorModel.getCategory();
        System.out.println(selectedCategory);

        if(selectedCategory != null){
            int spinnerPosition = myAdapter.getPosition(selectedCategory);
            //System.out.println(spinnerPosition);
            editcategoryadd.setSelection(spinnerPosition);
        }

        editcategoryadd.setOnItemSelectedListener(this);

        //selected Radio
        String selectedRadio = vendorModel.getStatus();
        System.out.println(selectedRadio);

        //setting the selected radio button
        if(selectedRadio.equals("Pending")){
            radioButton1.setChecked(true);
        }else{
            radioButton2.setChecked(true);
        }

        //App bar name
        getSupportActionBar().setTitle(R.string.appbar_name_edit_vendor);


        //get values from database and set them to the editVendor editText by using getters
        edname.setText(vendorModel.getVendorname());
        edcontactno.setText(vendorModel.getContactno());
        eddescription.setText(vendorModel.getDescription());
        edamount.setText(vendorModel.getAmount());

        System.out.println(id);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        text = adapterView.getItemAtPosition(i).toString();
        Toast.makeText(adapterView.getContext(),text,Toast.LENGTH_SHORT).show(); //Toast
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

           final String vId = getIntent().getStringExtra("id");
            //VendorModel vendorModel = dbHelper.getSingleVendor(Integer.parseInt(vId));         //convert string id to integer and assign it tp the getSingleVendor method in DBHelper

            //getting selected radio button from the radio group
            int selectedId = radioGroup.getCheckedRadioButtonId();
            radioButton = findViewById(selectedId);

            //get new values to the variables
            String updateName = edname.getText().toString();
            String updateContactNo = edcontactno.getText().toString();
            String updateDescription = eddescription.getText().toString();
            String updateAmount = edamount.getText().toString();
            //updatedDate = System.currentTimeMillis();           //get updated time as current system time

            //create model class object
            VendorModel vendorModel = new VendorModel(Integer.parseInt(vId),updateName,text,updateContactNo,updateDescription,radioButton.getText().toString(),updateAmount);
            int state = dbHelper.updateSingleVendor(vendorModel);
            System.out.println(state);
            Toast.makeText(context, "Successfully Updated", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(context,VenderList.class));                //redirect to the vendorList interface

            /*Intent intent = new Intent(EditVender.this, ViewVender.class);
            startActivity(intent);

            //Toast
            Context context = getApplicationContext();
            CharSequence message = "Details Saved";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, message, duration);
            toast.show();*/
        }

        if(id == android.R.id.home){
            Intent intent = new Intent(EditVender.this, VenderList.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}