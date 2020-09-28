package com.example.mywedding;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mywedding.GuestAdapter;
import com.example.mywedding.Database.DBHelper;
import com.example.mywedding.Guest;

import java.util.ArrayList;
import java.util.List;

public class allGuests extends AppCompatActivity {

    private ArrayAdapter adapter;
    GuestAdapter gadap;
    EditText theFilter;

    private ListView listview;    //list type variable
    private List<Guest> guests;
    private DBHelper dbhelper;
    Context context;
    private TextView guestCount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_guests);


        //btn1 = findViewById(R.id.btn_glist1);

        listview = findViewById(R.id.guestlist);
        guestCount = findViewById(R.id.guestcount); //reference xml textView which is used to display count
        context = this;

        guests = new ArrayList<>();  //initialize arraylist to allocate memory for arraylist
        DBHelper dbhelper = new DBHelper(this);

        //get guest entries count
         int Gcount = dbhelper.countGuest(); //get value of count and save it in the Gcount variable
         guestCount.setText(Gcount+" Guests are added");

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

      // populateListview();
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
            Intent intent = new Intent(allGuests.this,Home.class);
            startActivity(intent);
        }

        return true;
    }

    //new
    /*
    private void populateListview(){

        ListView evenlist = findViewById(R.id.guestlist);
        dbhelper = new DBHelper(this);

        //

        guests = new ArrayList<>();
        Cursor data = dbhelper.getListEvents();

        if (data.getCount() == 0) {

            Toast.makeText(allGuests.this, "event list is empty", Toast.LENGTH_LONG).show();
        }else
            while (data.moveToNext()) {

                //guests.add(data.getString(1));
                guests = dbhelper.getAllGuests();
                gadap = new GuestAdapter(this, android.R.layout.simple_list_item_1, guests);

                evenlist.setAdapter(gadap);



                theFilter = findViewById(R.id.Esearch);

                theFilter.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        (allGuests.this).gadap.getFilter().filter(charSequence);
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                });


            }

    }

    //new

     */


}