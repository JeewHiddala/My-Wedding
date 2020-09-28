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
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioGroup;

import com.example.mywedding.GuestAdapter;
import com.example.mywedding.Database.DBHelper;
import com.example.mywedding.Guest;

import java.util.ArrayList;
import java.util.List;

public class allGuests extends AppCompatActivity {


    private ListView listview;    //list type variable
    private List<Guest> guests;
    private DBHelper dbhelper;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_guests);


        //btn1 = findViewById(R.id.btn_glist1);

        listview = findViewById(R.id.guestlist);

        context = this;

        guests = new ArrayList<>();  //initialize arraylist to allocate memory for arraylist
        DBHelper dbhelper = new DBHelper(this);

        guests = dbhelper.getAllGuests(); //call the function in the DBHelper ,using dbhelper object and save the values using guests
        GuestAdapter adapter = new GuestAdapter(context,R.layout.single_guest,guests);
        listview.setAdapter(adapter);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.allguests);





        //retrieve list
       /*
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Guest guest = guests.get(i);
               // if (i == 0){
                    Intent intent = new Intent(view.getContext(),viewGuest.class);
                    startActivity(intent);
               // }
            }
        });*/
    }

    public void addGuest(View view){
        Intent intent = new Intent(allGuests.this,addGuest.class);
        startActivity(intent);
    }
    public void viewGuest(View view){
        Intent intent = new Intent(allGuests.this,viewGuest.class);
        startActivity(intent);
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();


        if(id == android.R.id.home){
            Intent intent = new Intent(allGuests.this, Home.class);
            startActivity(intent);
        }

        return true;
    }



}