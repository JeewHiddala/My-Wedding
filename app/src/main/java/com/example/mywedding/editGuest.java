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
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

import com.example.mywedding.Database.DBHelper;

public class editGuest extends AppCompatActivity {

    Switch aSwitch;
    EditText upGname, upGen, upNotes, upPhone, upAdd, upEmail;

    public String myExtra = "text";
    private Context context;
    private DBHelper dbhelper;
    ImageButton btn;

    RadioGroup radioGroup;
    RadioButton radioButton1;
    RadioButton radioButton2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_guest);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.editguest);

        dbhelper = new DBHelper(this);

        upGname = (EditText)findViewById(R.id.EditGuestName);
        upGen = (EditText)findViewById(R.id.editGuestGender);
        upNotes =(EditText)findViewById(R.id.editGuestNotes);
        radioGroup=(RadioGroup)findViewById(R.id.radioGroup2);
        upPhone=(EditText)findViewById(R.id.editguestPhone);
        upAdd=(EditText)findViewById(R.id.editguestAddress);
        upEmail=(EditText)findViewById(R.id.editguestEmail);

        btn = (ImageButton) findViewById(R.id.updateRet);

        final String id = getIntent().getStringExtra("id");
        final Guest guest = dbhelper.getSingleGuest(Integer.parseInt(id));
        getSupportActionBar().setTitle(guest.getGuestName());

        upGname.setText(guest.getGuestName());
        upGen.setText(guest.getGender());
        upNotes.setText(guest.getNotes());
         //radioGroup.setIn();
        upPhone.setText(guest.getPhone());
        upAdd.setText(guest.getAddress());
        upEmail.setText(guest.geteMail());




    }


    @Override
    protected void onResume() {
        super.onResume();
        btn = (ImageButton) findViewById(R.id.updateRet);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String id = getIntent().getStringExtra("id");

                String guestName = upGname.getText().toString();
                String guestGen = upGen.getText().toString();
                String guesNotes = upNotes.getText().toString();
                // String stat = radioGroup.getText().getString();
                String guestPhone = upPhone.getText().toString();
                String guestAdd = upAdd.getText().toString();
                String guestEmail = upEmail.getText().toString();

                Guest guest = new Guest(Integer.parseInt(id), guestName, guestGen, guesNotes, guestPhone, guestAdd, guestEmail);
                int state = dbhelper.singleGuest(guest);

                if (state>0) {
                     //startActivity(new Intent(context,allGuests.class));
                    Toast.makeText(editGuest.this, "Data successfully Updated", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(editGuest.this, allGuests.class);
                    startActivity(new Intent(editGuest.this,allGuests.class));
                }
            }
        });

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

        if(id == R.id.done){
            Intent intent = new Intent(editGuest.this,allGuests.class);
            startActivity(intent);

            Context context = getApplicationContext();
            CharSequence message = "Changes Saved";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, message, duration);
            toast.show();
        }

        if(id == android.R.id.home){
            Intent intent = new Intent(editGuest.this,viewGuest.class);
            startActivity(intent);
        }
        return true;
    }




}