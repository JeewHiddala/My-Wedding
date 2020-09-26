package com.example.mywedding;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mywedding.Database.DBHelper;

public class viewGuest extends AppCompatActivity {

    TextView etGuestName, etguestGender, etGuestNotes, etStatus, etGuestPhone,etGuestAddress, etEmail;
    public String myExtra = "text";
    private DBHelper dbhelper;
    private Context context;

    ImageButton btnedit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_guest);

        dbhelper = new DBHelper(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
       // getSupportActionBar().setTitle(R.string.viewguest);

        etGuestName = findViewById(R.id.edit_Guest_name);
        etguestGender = findViewById(R.id.edit_Guest_Gender);
        etGuestNotes = findViewById(R.id.edit_Guest_notes);
        etStatus = findViewById(R.id.edit_Guest_status);
        etGuestPhone = findViewById(R.id.edit_Guest_phone);
        etGuestAddress = findViewById(R.id.edit_Guest_add);
        etEmail = findViewById(R.id.edit_Guest_email);

        btnedit = (ImageButton)findViewById(R.id.editbtn);

        final String id = getIntent().getStringExtra("id");
        final Guest guest = dbhelper.getSingleGuest(Integer.parseInt(id));
        getSupportActionBar().setTitle(guest.getGuestName());

        etGuestName.setText(guest.getGuestName());
        etguestGender.setText(guest.getGender());
        etGuestNotes.setText(guest.getNotes());
        etStatus.setText(guest.getStatus());
        etGuestPhone.setText(guest.getPhone());
        etGuestAddress.setText(guest.getAddress());
        etEmail.setText(guest.geteMail());


        //etGuestName.setText()

    btnedit.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {

        Intent myIntent = new Intent(viewGuest.this, editGuest.class);
        myIntent.putExtra("id",String.valueOf(guest.getId()));
        Context context = getApplicationContext();
        CharSequence text = "edit";
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context,text,duration);
        toast.show();

        toast.setGravity(Gravity.BOTTOM|Gravity.CENTER, 0, 10);
        myIntent.putExtra("MAIN_EXTRA", myExtra);
        startActivity(myIntent);
    }
});






    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.edit){
            Intent intent = new Intent(viewGuest.this,editGuest.class);
            startActivity(intent);
        }


        if(id == android.R.id.home){
            Intent intent = new Intent(viewGuest.this,allGuests.class);
            startActivity(intent);
        }

        return true;
    }
}