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
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.example.mywedding.Database.DBHelper;

public class addGuest extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner categorySpinner;
    RadioGroup myRadioGroup;
    EditText etguestname,etgender,etnotes,etstatus,etphone,etaddress,etemail;
    String category;



    //Switch aSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_guest);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setTitle(R.string.guestadd);

        etguestname = findViewById(R.id.editTextGuestName);
       // etgender = findViewById(R.id.editTextGender);

        //spinner
        categorySpinner = findViewById(R.id.editTextGender);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.guest_gender,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(adapter);
        categorySpinner.setOnItemSelectedListener(this);



        etnotes = findViewById(R.id.editTextNotes);
      //  myRadioGroup = findViewById(R.id.radioGroup2);
        etphone = findViewById(R.id.addguestPhone);
        etaddress = findViewById(R.id.addguestAddress);
        etemail = findViewById(R.id.addguestEmail);





    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_save,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        //radiogroup
        //int selectedId = myRadioGroup.getCheckedRadioButtonId();
       // myRadioGroup = findViewById(selectedId);


        if(id == R.id.done){

            DBHelper dbhelper = new DBHelper(this);

            long val = dbhelper.addGuest(etguestname.getText().toString(), category, etnotes.getText().toString(), 0, etphone.getText().toString(), etaddress.getText().toString(), etemail.getText().toString());

            if(val>0){
                Toast.makeText(this, "Guest Added", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(addGuest.this,allGuests.class);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Data not inserted", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(addGuest.this,allGuests.class);
                startActivity(intent);

            }

               // Intent intent = new Intent(addGuest.this,allGuests.class);
              //  startActivity(intent);

              //  Context context = getApplicationContext();
             //   CharSequence message = "Guest Added";
              //  int duration = Toast.LENGTH_SHORT;
             //   Toast toast = Toast.makeText(context, message, duration);
              //  toast.show();
        }

        if(id == android.R.id.home){
            Intent intent = new Intent(addGuest.this,allGuests.class);
            startActivity(intent);
        }
        return true;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        category = adapterView.getItemAtPosition(i).toString();
        Toast.makeText(adapterView.getContext(), category, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

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