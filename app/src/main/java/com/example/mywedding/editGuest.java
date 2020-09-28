package com.example.mywedding;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
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

    
    EditText upGname, upGen, upNotes, upPhone, upAdd, upEmail;

    public String myExtra = "text";
    private Context context;
    private DBHelper dbhelper;
    ImageButton btn;

    RadioGroup radioGroup;
    RadioButton radioButton1;
    RadioButton radioButton2;
    RadioButton radioButton3;
    RadioButton radioButton;
    int selectedID;
    String val;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        dbhelper = new DBHelper(this);
        final String id = getIntent().getStringExtra("id");
        final Guest guest = dbhelper.getSingleGuest(Integer.parseInt(id));
        getSupportActionBar().setTitle(guest.getGuestName());

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_guest);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.editguest);

        upGname = (EditText)findViewById(R.id.EditGuestName);
        upGen = (EditText)findViewById(R.id.editGuestGender);
        upNotes =(EditText)findViewById(R.id.editGuestNotes);
        radioGroup=(RadioGroup)findViewById(R.id.radioGroupst);
        radioButton1=(RadioButton)findViewById(R.id.bri);
        radioButton2=(RadioButton)findViewById(R.id.groom2);

        val = guest.getStatus();
        if(val.equals("Sent")){
            radioButton1.setChecked(true);
        } else {
            radioButton2.setChecked(true);
        }

        upPhone=(EditText)findViewById(R.id.editguestPhone);
        upAdd=(EditText)findViewById(R.id.editguestAddress);
        upEmail=(EditText)findViewById(R.id.editguestEmail);

//        btn = (ImageButton) findViewById(R.id.updateRet);

        upGname.setText(guest.getGuestName());
        upGen.setText(guest.getGender());
        upNotes.setText(guest.getNotes());
         //radioGroup.setIn();
        upPhone.setText(guest.getPhone());
        upAdd.setText(guest.getAddress());
        upEmail.setText(guest.geteMail());

    }




    /*@Override
    protected void onResume() {
        super.onResume();
        btn = (ImageButton) findViewById(R.id.updateRet);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int selectedId = radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(selectedId);
                final String Gid = getIntent().getStringExtra("id");

                //int selectedId = myRadioGroup.getCheckedRadioButtonId();
               // radioButton = findViewById(selectedId);

                String guestName = upGname.getText().toString();
                String guestGen = upGen.getText().toString();
                String guesNotes = upNotes.getText().toString();
                // String stat = radioGroup.getText().getString();
                String guestPhone = upPhone.getText().toString();
                String guestAdd = upAdd.getText().toString();
                String guestEmail = upEmail.getText().toString();
                String state1 = radioButton.getText().toString();
                Guest guest = new Guest(Integer.parseInt(Gid), guestName, guestGen, guesNotes, state1, guestPhone, guestAdd, guestEmail);
                int state = dbhelper.singleGuest(guest);

                if (state>0) {
                     //startActivity(new Intent(context,allGuests.class));
                    Toast.makeText(editGuest.this, "Data successfully Updated", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(editGuest.this, allGuests.class);
                    startActivity(new Intent(editGuest.this,allGuests.class));
                }
            }
        });

    }*/



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

    /*
            int selectedId = radioGroup.getCheckedRadioButtonId();
            radioButton3 = findViewById(selectedId);
            final String Gid = getIntent().getStringExtra("id");

            String guestName = upGname.getText().toString();
            String guestGen = upGen.getText().toString();
            String guesNotes = upNotes.getText().toString();
             String stat = radioButton3.getText().toString();
            String guestPhone = upPhone.getText().toString();
            String guestAdd = upAdd.getText().toString();
            String guestEmail = upEmail.getText().toString();

            Guest guest = new Guest(Integer.parseInt(Gid), guestName, guestGen, guesNotes, stat, guestPhone, guestAdd, guestEmail);
           int state = dbhelper.singleGuest(guest);
*/
            /*
            if (state>0) {
                //startActivity(new Intent(context,allGuests.class));
                Toast.makeText(editGuest.this, "Data successfully Updated", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(editGuest.this, allGuests.class);
                startActivity(new Intent(editGuest.this,allGuests.class));
            }
*/
            /*
            Intent intent = new Intent(editGuest.this,allGuests.class);
            startActivity(intent);

            Context context = getApplicationContext();
            CharSequence message = "Changes Saved";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, message, duration);
            toast.show();

             */


            int selectedId = radioGroup.getCheckedRadioButtonId();
            radioButton = findViewById(selectedId);
            final String Gid = getIntent().getStringExtra("id");

            //int selectedId = myRadioGroup.getCheckedRadioButtonId();
            // radioButton = findViewById(selectedId);

            String guestName = upGname.getText().toString();
            String guestGen = upGen.getText().toString();
            String guesNotes = upNotes.getText().toString();
            // String stat = radioGroup.getText().getString();
            String guestPhone = upPhone.getText().toString();
            String guestAdd = upAdd.getText().toString();
            String guestEmail = upEmail.getText().toString();
            String state1 = radioButton.getText().toString();
            Guest guest = new Guest(Integer.parseInt(Gid), guestName, guestGen, guesNotes, state1, guestPhone, guestAdd, guestEmail);
            int state = dbhelper.singleGuest(guest);

            if (state>0) {
                //startActivity(new Intent(context,allGuests.class));
                Toast.makeText(editGuest.this, "Data successfully Updated", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(editGuest.this, allGuests.class);
                startActivity(new Intent(editGuest.this,allGuests.class));
            }






        }

        if(id == android.R.id.home){
            Intent intent = new Intent(editGuest.this, allGuests.class);
            startActivity(intent);
        }
        return true;
    }




}