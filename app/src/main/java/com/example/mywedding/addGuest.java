package com.example.mywedding;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.example.mywedding.Database.DBHelper;

public class addGuest extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner categorySpinner;
    RadioGroup myRadioGroup;
    EditText etguestname, etgender, etnotes, etstatus, etphone, etaddress, etemail;
    String category;
    RadioButton radioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_guest);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //the title displayed in the menu bar of add guest page
        getSupportActionBar().setTitle(R.string.guestadd);

        etguestname = findViewById(R.id.editTextGuestName);
        // etgender = findViewById(R.id.editTextGender);

        //spinner for gender
        categorySpinner = findViewById(R.id.editTextGender);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.guest_gender, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(adapter);
        categorySpinner.setOnItemSelectedListener(this);

        etnotes = findViewById(R.id.editTextNotes);
        //  myRadioGroup = findViewById(R.id.radioGroup2);
        etphone = findViewById(R.id.addguestPhone);
        etaddress = findViewById(R.id.addguestAddress);
        etemail = findViewById(R.id.addguestEmail);


        myRadioGroup = findViewById(R.id.radioGroupst);


    }

    //validating email address
    public boolean validateEmail(EditText etemail) {
        String emailInput = etemail.getText().toString();

        if (!emailInput.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            Toast.makeText(this, "Email Address successfully validated", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            Toast.makeText(this, "Invalid Email", Toast.LENGTH_SHORT).show();
            return false;
        }
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        category = adapterView.getItemAtPosition(i).toString();
        Toast.makeText(adapterView.getContext(), category, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_save, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.done) {
            //taking the value of radiobutton
            int selectedId = myRadioGroup.getCheckedRadioButtonId();
            radioButton = findViewById(selectedId);

            //required fields validations
            if (TextUtils.isEmpty(etguestname.getText())) {
                Toast.makeText(this, "Enter Guest Name", Toast.LENGTH_SHORT).show();
                etguestname.setError("Guest Name is required");
            } else if (TextUtils.isEmpty(etphone.getText())) {
                Toast.makeText(this, "Enter Phone Number", Toast.LENGTH_SHORT).show();
                etphone.setError("Phone Number is Required");
            } else {
                DBHelper dbhelper = new DBHelper(this);  //creating database object

                //inserting user inputs ,using database object
                long val = dbhelper.addGuest(etguestname.getText().toString(), category, etnotes.getText().toString(), radioButton.getText().toString(), etphone.getText().toString(), etaddress.getText().toString(), etemail.getText().toString());

                //notify the user if the values are added succussfully or not
                if (val > 0) {
                    Toast.makeText(this, "Guest Added", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(addGuest.this, allGuests.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "Data not inserted", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(addGuest.this, allGuests.class);
                    startActivity(intent);
                }

            }
            //validateEmail(etemail);


            //back button
            if (id == android.R.id.home) {
                Intent intent = new Intent(addGuest.this, allGuests.class);
                startActivity(intent);
            }
//            return true;
        }
        return true;
    }


        //public void addGuest(View view){
       /* DBHelper dbhelper = new DBHelper(this);

        long val = dbhelper.addGuest(etguestname.getText().toString(),etgender.getText().toString(),etnotes.getText().toString() ,etstatus.getText().toString(),etphone.getText().toString(),etaddress.getText().toString(),etemail.getText().toString());

        if(val>0){
            Toast.makeText(this, "Data Successfully Inserted", Toast.LENGTH_SHORT).show();

        }else{
            Toast.makeText(this, "Data not inserted", Toast.LENGTH_SHORT).show();
        }
*/


        //}


}

